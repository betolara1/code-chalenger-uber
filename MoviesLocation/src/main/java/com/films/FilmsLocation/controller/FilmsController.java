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
        
        // CASO O USUARIO DIGITE UM TITULO QUE CONTENHA ASPAS, ESSA LINHA NÃO DEIXA "QUEBRAR" A QUERY
        String treatedTitleString = title.replace("'", "''");

        // 1. MONTAMOS A QUERY SQL DINAMICAMENTE
        String queryDinamica = "SELECT * WHERE title='" + treatedTitleString + "'";

        // 2. PASSAMOS PARA O FEIGN
        return sfFilms.getNameFilm(appToken, queryDinamica, 1, 10);
    }
}
