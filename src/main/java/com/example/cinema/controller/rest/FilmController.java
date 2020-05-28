package com.example.cinema.controller.rest;

import com.example.cinema.dto.FilmDto;
import com.example.cinema.entity.Film;
import com.example.cinema.mapper.FilmMapper;
import com.example.cinema.service.FilmService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api/film", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    @ApiOperation("Get all films list.")
    public List<Film> getAllFilms() {
        return filmService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new film.")
    public Film createFilm(@RequestBody @Valid FilmDto filmDto) {
        Film film = FilmMapper.INSTANCE.toEntity(filmDto);
        return filmService.create(film);
    }
}
