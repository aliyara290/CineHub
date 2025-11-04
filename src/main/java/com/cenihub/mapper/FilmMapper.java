package com.cenihub.mapper;

import com.cenihub.dto.request.FilmRequestDTO;
import com.cenihub.dto.response.FilmResponseDTO;
import com.cenihub.model.Category;
import com.cenihub.model.Director;
import com.cenihub.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public Film toEntity(FilmRequestDTO dto, Director director, Category category) {
        return Film.builder()
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear())
                .duration(dto.getDuration())
                .synopsis(dto.getSynopsis())
                .rating(dto.getRating())
                .director(director)
                .category(category)
                .build();
    }

    public FilmResponseDTO toResponseDTO(Film film) {
        return FilmResponseDTO.builder()
                .id(film.getId())
                .title(film.getTitle())
                .releaseYear(film.getReleaseYear())
                .duration(film.getDuration())
                .synopsis(film.getSynopsis())
                .rating(film.getRating())
                .directorName(film.getDirector() != null ? film.getDirector().getName() : null)
                .categoryName(film.getCategory() != null ? film.getCategory().getName() : null)
                .build();
    }

    public void updateEntityFromDTO(FilmRequestDTO dto, Film film, Director director, Category category) {
        film.setTitle(dto.getTitle());
        film.setReleaseYear(dto.getReleaseYear());
        film.setDuration(dto.getDuration());
        film.setSynopsis(dto.getSynopsis());
        film.setRating(dto.getRating());
        film.setDirector(director);
        film.setCategory(category);
    }
}