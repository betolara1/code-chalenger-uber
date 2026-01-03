package com.films.FilmsLocation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.films.FilmsLocation.dto.FilmsDTO;

@FeignClient(name = "SfFilms", url = "https://data.sfgov.org/api/v3")
public interface SfFilms {

    @GetMapping("/views/yitu-d5am/query.json")
    List<FilmsDTO> getAllFilms(@RequestParam("app_token") String appToken);


    @GetMapping("/views/yitu-d5am/query.json")
    List<FilmsDTO> getNameFilm(
                            @RequestParam("app_token") String appToken,
                            @RequestParam("query") String sqlQuery,    // A sua string "SELECT..."
                            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
                        );
}
