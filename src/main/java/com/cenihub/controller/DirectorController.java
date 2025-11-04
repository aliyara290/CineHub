package com.cenihub.controller;

import com.cenihub.dto.request.DirectorRequestDTO;
import com.cenihub.dto.response.DirectorResponseDTO;
import com.cenihub.service.interfaces.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> createDirector(@Valid @RequestBody DirectorRequestDTO requestDTO) {
        DirectorResponseDTO response = directorService.createDirector(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> updateDirector(
            @PathVariable("id") Long id,
            @Valid @RequestBody DirectorRequestDTO requestDTO) {
        DirectorResponseDTO response = directorService.updateDirector(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getDirectorById(@PathVariable("id") Long id) {
        DirectorResponseDTO response = directorService.getDirectorById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAllDirectors() {
        List<DirectorResponseDTO> directors = directorService.getAllDirectors();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/search")
    public ResponseEntity<DirectorResponseDTO> getDirectorByName(@RequestParam("name") String name) {
        DirectorResponseDTO response = directorService.getDirectorByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<DirectorResponseDTO>> getDirectorsByNationality(@PathVariable("nationality") String nationality) {
        List<DirectorResponseDTO> directors = directorService.getDirectorsByNationality(nationality);
        return ResponseEntity.ok(directors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}