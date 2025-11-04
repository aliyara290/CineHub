package com.cenihub.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;

    @NotNull(message = "Release year is required")
    private LocalDate releaseYear;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    @NotBlank(message = "Synopsis is required")
    @Size(max = 2000, message = "Synopsis cannot exceed 2000 characters")
    private String synopsis;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0")
    private Double rating;

    @NotNull(message = "Director ID is required")
    private Long directorId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}