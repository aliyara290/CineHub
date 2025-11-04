package com.cenihub.mapper;

import com.cenihub.dto.request.DirectorRequestDTO;
import com.cenihub.dto.response.DirectorResponseDTO;
import com.cenihub.model.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {

    public Director toEntity(DirectorRequestDTO dto) {
        return Director.builder()
                .name(dto.getName())
                .nationality(dto.getNationality())
                .birthYear(dto.getBirthYear())
                .build();
    }

    public DirectorResponseDTO toResponseDTO(Director director) {
        return DirectorResponseDTO.builder()
                .id(director.getId())
                .name(director.getName())
                .nationality(director.getNationality())
                .birthYear(director.getBirthYear())
                .filmCount(director.getFilms() != null ? director.getFilms().size() : 0)
                .createdAt(director.getCreatedAt())
                .updatedAt(director.getUpdatedAt())
                .build();
    }

    public void updateEntityFromDTO(DirectorRequestDTO dto, Director director) {
        director.setName(dto.getName());
        director.setNationality(dto.getNationality());
        director.setBirthYear(dto.getBirthYear());
    }
}