package com.cenihub.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmResponseDTO {
    private String id;
    private String title;
    private LocalDate releaseYear;
    private Integer duration;
    private String synopsis;
    private Double rating;
    private String directorName;
    private String categoryName;
}