package com.cenihub.service.interfaces;

import com.cenihub.dto.request.FilmRequestDTO;
import com.cenihub.dto.response.FilmResponseDTO;
import com.cenihub.model.Film;

import java.util.List;

public interface FilmService {
    FilmResponseDTO createFilm(FilmRequestDTO requestDTO);
    FilmResponseDTO getFilmById(String id);
    List<FilmResponseDTO> getAllFilms();
    List<FilmResponseDTO> getFilmsByDirector(Long directorId);
    List<FilmResponseDTO> getFilmsByCategory(Long categoryId);
    FilmResponseDTO updateFilm(String id, FilmRequestDTO requestDTO);
    List<FilmResponseDTO> getFilmsByDirectorName(String directorName);
    void deleteFilm(String id);
}