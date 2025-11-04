package com.cenihub.service.impl;

import com.cenihub.dto.request.FilmRequestDTO;
import com.cenihub.dto.response.FilmResponseDTO;
import com.cenihub.exception.DuplicateResourceException;
import com.cenihub.exception.FailedToInsertToDb;
import com.cenihub.exception.RecordNotFound;
import com.cenihub.mapper.FilmMapper;
import com.cenihub.model.Category;
import com.cenihub.model.Director;
import com.cenihub.model.Film;
import com.cenihub.repository.CategoryRepository;
import com.cenihub.repository.DirectorRepository;
import com.cenihub.repository.FilmRepository;
import com.cenihub.service.interfaces.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;
    private final FilmMapper filmMapper;

    @Override
    public FilmResponseDTO createFilm(FilmRequestDTO requestDTO) {
        if (filmRepository.existsByTitle(requestDTO.getTitle())) {
            throw new DuplicateResourceException("Film with title " + requestDTO.getTitle() + " already exists!");
        }

        Director director = directorRepository.findById(requestDTO.getDirectorId())
                .orElseThrow(() -> new RecordNotFound());

        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RecordNotFound());

        try {
            Film film = filmMapper.toEntity(requestDTO, director, category);
            Film savedFilm = filmRepository.save(film);
            return filmMapper.toResponseDTO(savedFilm);
        } catch (Exception ex) {
            throw new FailedToInsertToDb(ex.getCause());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public FilmResponseDTO getFilmById(String id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());
        return filmMapper.toResponseDTO(film);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByDirector(Long directorId) {
        return filmRepository.findByDirectorId(directorId)
                .stream()
                .map(filmMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByCategory(Long categoryId) {
        return filmRepository.findByCategoryId(categoryId)
                .stream()
                .map(filmMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmResponseDTO updateFilm(String id, FilmRequestDTO requestDTO) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());

        if (!film.getTitle().equals(requestDTO.getTitle()) &&
                filmRepository.existsByTitle(requestDTO.getTitle())) {
            throw new DuplicateResourceException("Film with title " + requestDTO.getTitle() + " already exists!");
        }

        Director director = directorRepository.findById(requestDTO.getDirectorId())
                .orElseThrow(() -> new RecordNotFound());

        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RecordNotFound());

        filmMapper.updateEntityFromDTO(requestDTO, film, director, category);
        Film updatedFilm = filmRepository.save(film);
        return filmMapper.toResponseDTO(updatedFilm);
    }

    @Override
    public void deleteFilm(String id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());
        filmRepository.delete(film);
    }

    @Override
    public List<FilmResponseDTO> getFilmsByDirectorName(String directorName) {
        List<Film> getFilmByDirectoryName = filmRepository.getFilmsByDirectorName(directorName);
        return getFilmByDirectoryName.stream().map(filmMapper::toResponseDTO).collect(Collectors.toList());
    }
}