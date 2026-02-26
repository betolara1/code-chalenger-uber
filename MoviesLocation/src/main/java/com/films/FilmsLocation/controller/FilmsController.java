package com.films.FilmsLocation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.films.FilmsLocation.client.SfFilms;
import com.films.FilmsLocation.dto.FilmsDTO;


@RestController
@RequestMapping("/api/films")
public class FilmsController {

    @Autowired
    private SfFilms sfFilms;

    @Value("${app_token}")
    private String appToken;

    @GetMapping("/allFilms")
    public List<FilmsDTO> getAllFilms(){
        List<FilmsDTO> filmsDTO = sfFilms.getAllFilms(appToken);

        return filmsDTO;
    }

    @GetMapping("/search")
    public List<FilmsDTO> getNameFilms(@RequestParam("title") String title){
        
        // TRATAMENTO PARA ASPAS SIMPLES NO TÍTULO, POIS SE O USUÁRIO DIGITAR UM TÍTULO COM ASPAS, ISSO PODE QUEBRAR A QUERY SQL
        String treatedTitleString = title.replace("'", "''");

        // 1. MONTAMOS A QUERY SQL DINAMICAMENTE
        String queryDinamica = "SELECT * WHERE title='" + treatedTitleString + "'";

        // 2. PASSAMOS PARA O FEIGN
        // O FEIGN VAI ENVIAR ESSA QUERY PARA A API DE FILMES DE SÃO FRANCISCO, QUE VAI RETORNAR OS FILMES QUE TIVEREM O TÍTULO EXATAMENTE 
        // IGUAL AO QUE O USUÁRIO DIGITOU
        return sfFilms.getNameFilm(appToken, queryDinamica, 1, 10);
    }
}
