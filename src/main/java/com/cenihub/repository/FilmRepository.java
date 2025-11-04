package com.cenihub.repository;

import com.cenihub.dto.response.FilmResponseDTO;
import com.cenihub.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {
    Optional<Film> findByTitle(String title);
    List<Film> findByDirectorId(Long directorId);
    List<Film> findByCategoryId(Long categoryId);
    List<Film> getFilmsByDirectorName(String directorName);
    boolean existsByTitle(String title);
}