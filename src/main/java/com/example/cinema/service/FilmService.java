package com.example.cinema.service;

import com.example.cinema.entity.Film;
import com.example.cinema.exception.RestException;
import com.example.cinema.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    public Film findFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new RestException("Not found film with id: " + id, HttpStatus.NOT_FOUND));
    }

    public Film findTheOnlyFilmId() {
        return filmRepository.findAll().stream().findFirst().orElseThrow(
                () -> new RestException("Not found film.", HttpStatus.NOT_FOUND));
    }
}
