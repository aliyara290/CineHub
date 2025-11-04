package com.cenihub.service.interfaces;

import com.cenihub.dto.request.DirectorRequestDTO;
import com.cenihub.dto.response.DirectorResponseDTO;

import java.util.List;

public interface DirectorService {
    DirectorResponseDTO createDirector(DirectorRequestDTO requestDTO);
    List<DirectorResponseDTO> getAllDirectors();
    DirectorResponseDTO getDirectorById(Long id);
    DirectorResponseDTO getDirectorByName(String name);
    List<DirectorResponseDTO> getDirectorsByNationality(String nationality);
    DirectorResponseDTO updateDirector(Long id, DirectorRequestDTO requestDTO);
    void deleteDirector(Long id);
}