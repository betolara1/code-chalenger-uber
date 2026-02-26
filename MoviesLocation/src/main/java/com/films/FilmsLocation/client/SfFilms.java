package com.films.FilmsLocation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.films.FilmsLocation.dto.FilmsDTO;

// Interface para acessar a API de filmes de São Francisco usando Feign Client
// O nome do cliente é "SfFilms" e a URL base é "https://data.sfgov.org/api/v3"
@FeignClient(name = "SfFilms", url = "https://data.sfgov.org/api/v3")
public interface SfFilms {

    // Método para obter todos os filmes, usando o endpoint "/views/yitu-d5am/query.json"
    // O parâmetro "app_token" é necessário para autenticação na API
    @GetMapping("/views/yitu-d5am/query.json")
    List<FilmsDTO> getAllFilms(@RequestParam("app_token") String appToken);

    // Método para obter filmes por nome, usando o mesmo endpoint, mas com um parâmetro de consulta adicional "query"
    // O parâmetro "query" deve conter a string SQL para filtrar os resultados, por exemplo: "SELECT * WHERE title = 'Inception'"
    // Os parâmetros "pageNumber" e "pageSize" são opcionais para controle de paginação dos resultados
    // Exemplo de uso: getNameFilm("your_app_token", "SELECT * WHERE title = 'Inception'", 1, 10)
    @GetMapping("/views/yitu-d5am/query.json")
    List<FilmsDTO> getNameFilm(
                            @RequestParam("app_token") String appToken,
                            @RequestParam("query") String sqlQuery,    // A sua string "SELECT..."
                            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
                        );
}
