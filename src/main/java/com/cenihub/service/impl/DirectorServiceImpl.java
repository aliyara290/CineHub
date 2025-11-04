package com.cenihub.service.impl;

import com.cenihub.dto.request.DirectorRequestDTO;
import com.cenihub.dto.response.DirectorResponseDTO;
import com.cenihub.exception.DuplicateResourceException;
import com.cenihub.exception.FailedToInsertToDb;
import com.cenihub.exception.RecordNotFound;
import com.cenihub.mapper.DirectorMapper;
import com.cenihub.model.Director;
import com.cenihub.repository.DirectorRepository;
import com.cenihub.service.interfaces.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public DirectorResponseDTO createDirector(DirectorRequestDTO requestDTO) {
        if (directorRepository.existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException("Director with name " + requestDTO.getName() + " already exists!");
        }
        try {
            Director director = directorMapper.toEntity(requestDTO);
            Director savedDirector = directorRepository.save(director);
            return directorMapper.toResponseDTO(savedDirector);
        } catch (Exception ex) {
            throw new FailedToInsertToDb(ex.getCause());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirectorResponseDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DirectorResponseDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());
        return directorMapper.toResponseDTO(director);
    }

    @Override
    @Transactional(readOnly = true)
    public DirectorResponseDTO getDirectorByName(String name) {
        Director director = directorRepository.findByName(name)
                .orElseThrow(() -> new RecordNotFound());
        return directorMapper.toResponseDTO(director);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirectorResponseDTO> getDirectorsByNationality(String nationality) {
        return directorRepository.findByNationality(nationality)
                .stream()
                .map(directorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorResponseDTO updateDirector(Long id, DirectorRequestDTO requestDTO) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());

        // Check if new name already exists (and it's not the current director)
        if (!director.getName().equals(requestDTO.getName()) &&
                directorRepository.existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException("Director with name " + requestDTO.getName() + " already exists!");
        }

        directorMapper.updateEntityFromDTO(requestDTO, director);
        Director updatedDirector = directorRepository.save(director);
        return directorMapper.toResponseDTO(updatedDirector);
    }

    @Override
    public void deleteDirector(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());
        directorRepository.delete(director);
    }
}