package com.films.FilmsLocation.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FilmsDTO {
    private String title;
    private String locations;
    private String director;
    private Object point;
    private Double longitude;
    private Double latitude;
    private LocalDateTime data_as_of;
}
