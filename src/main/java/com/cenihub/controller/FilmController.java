package com.cenihub.controller;

import com.cenihub.dto.request.FilmRequestDTO;
import com.cenihub.dto.response.FilmResponseDTO;
import com.cenihub.service.interfaces.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<FilmResponseDTO> createFilm(@Valid @RequestBody FilmRequestDTO requestDTO) {
        FilmResponseDTO response = filmService.createFilm(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> updateFilm(
            @PathVariable("id") String id,
            @Valid @RequestBody FilmRequestDTO requestDTO) {
        FilmResponseDTO response = filmService.updateFilm(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable("id") String id) {
        FilmResponseDTO response = filmService.getFilmById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FilmResponseDTO>> getAllFilms() {
        List<FilmResponseDTO> films = filmService.getAllFilms();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/director/{directorId}")
    public ResponseEntity<List<FilmResponseDTO>> getFilmsByDirector(@PathVariable("directorId") Long directorId) {
        List<FilmResponseDTO> films = filmService.getFilmsByDirector(directorId);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<FilmResponseDTO>> getFilmsByCategory(@PathVariable("categoryId") Long categoryId) {
        List<FilmResponseDTO> films = filmService.getFilmsByCategory(categoryId);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") String id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmResponseDTO>> getFilmsByDirectorName(@RequestParam("name") String name) {
        List<FilmResponseDTO> films = filmService.getFilmsByDirectorName(name);
        return ResponseEntity.ok().body(films);
    }

}