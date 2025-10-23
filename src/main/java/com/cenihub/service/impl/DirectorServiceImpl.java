package com.cenihub.service.impl;

import com.cenihub.dto.request.DirectorRequestDTO;
import com.cenihub.mapper.DirectorMapper;
import com.cenihub.model.Director;
import com.cenihub.repository.DirectorRepository;
import com.cenihub.service.interfaces.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public Director createDirector(DirectorRequestDTO directorRequestDTO) {
        // Check if director already exists
        if (directorRepository.existsByName(directorRequestDTO.getName())) {
            throw new RuntimeException("Director with name '" + directorRequestDTO.getName() + "' already exists");
        }

        // Convert DTO to Entity
        Director director = directorMapper.toEntity(directorRequestDTO);

        // Save and return
        return directorRepository.save(director);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Director> getDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Director> getDirectorByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Director> getDirectorsByNationality(String nationality) {
        return directorRepository.findByNationality(nationality);
    }

    @Override
    public Director updateDirector(Long id, DirectorRequestDTO directorRequestDTO) {
        // Find existing director
        Director existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + id));

        // Update fields
        existingDirector.setName(directorRequestDTO.getName());
        existingDirector.setNationality(directorRequestDTO.getNationality());
        existingDirector.setBirthYear(directorRequestDTO.getBirthYear());

        // Save and return
        return directorRepository.save(existingDirector);
    }

    @Override
    public void deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new RuntimeException("Director not found with id: " + id);
        }
        directorRepository.deleteById(id);
    }
}