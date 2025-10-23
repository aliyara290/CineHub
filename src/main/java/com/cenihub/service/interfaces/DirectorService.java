package com.cenihub.service.interfaces;

import com.cenihub.dto.request.DirectorRequestDTO;
import com.cenihub.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    Director createDirector(DirectorRequestDTO directorRequestDTO);
    List<Director> getAllDirectors();
    Optional<Director> getDirectorById(Long id);
    Optional<Director> getDirectorByName(String name);
    List<Director> getDirectorsByNationality(String nationality);
    Director updateDirector(Long id, DirectorRequestDTO directorRequestDTO);
    void deleteDirector(Long id);
}