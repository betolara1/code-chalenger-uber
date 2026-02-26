package com.films.FilmsLocation.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.films.FilmsLocation.client.SfFilms;
import com.films.FilmsLocation.dto.FilmsDTO;

@WebMvcTest(FilmsController.class)
public class FilmsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SfFilms sfFilms;

    @Test
    @DisplayName("Should return all films successfully")
    void shouldReturnAllFilms() throws Exception {
        FilmsDTO film1 = new FilmsDTO();
        film1.setTitle("Film 1");
        FilmsDTO film2 = new FilmsDTO();
        film2.setTitle("Film 2");
        List<FilmsDTO> films = Arrays.asList(film1, film2);

        when(sfFilms.getAllFilms(anyString())).thenReturn(films);

        mockMvc.perform(get("/api/films/allFilms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
    }

    @Test
    @DisplayName("Should search films by title successfully")
    void shouldSearchFilmsByTitle() throws Exception {
        FilmsDTO film = new FilmsDTO();
        film.setTitle("Inception");
        List<FilmsDTO> films = List.of(film);

        when(sfFilms.getNameFilm(anyString(), anyString(), anyInt(), anyInt())).thenReturn(films);

        mockMvc.perform(get("/api/films/search").param("title", "Inception"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Inception"));
    }

    @Test
    @DisplayName("Should handle single quotes in title search")
    void shouldHandleSingleQuotesInTitle() throws Exception {
        FilmsDTO film = new FilmsDTO();
        film.setTitle("L'Amour");
        List<FilmsDTO> films = List.of(film);

        // Expected query should have doubled single quotes
        String expectedQuery = "SELECT * WHERE title='L''Amour'";

        when(sfFilms.getNameFilm(anyString(), eq(expectedQuery), anyInt(), anyInt())).thenReturn(films);

        mockMvc.perform(get("/api/films/search").param("title", "L'Amour"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("L'Amour"));
    }

    @Test
    @DisplayName("Should return empty list when no films found in search")
    void shouldReturnEmptyListWhenNoFilmsFound() throws Exception {
        when(sfFilms.getNameFilm(anyString(), anyString(), anyInt(), anyInt())).thenReturn(List.of());

        mockMvc.perform(get("/api/films/search").param("title", "NonExistentFilm"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Should return empty list when all films call returns empty")
    void shouldReturnEmptyListWhenAllFilmsEmpty() throws Exception {
        when(sfFilms.getAllFilms(anyString())).thenReturn(List.of());

        mockMvc.perform(get("/api/films/allFilms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
