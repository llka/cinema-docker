package com.example.cinema.service;

import com.example.cinema.entity.Film;
import com.example.cinema.exception.RestException;
import com.example.cinema.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    public Film findFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new RestException("Not found film with id: " + id, HttpStatus.NOT_FOUND));
    }

    public Film getAvengersFilm() {
        return findFilmByTitle("Avengers");
    }

    public Film findFilmByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new RestException("To find film provide non empty title!", HttpStatus.BAD_REQUEST);
        }
        return filmRepository.findByTitleContainingIgnoreCase(title).orElseThrow(
                () -> new RestException("Not found film by title: " + title, HttpStatus.NOT_FOUND));
    }

    public List<Film> findAll(){
        return filmRepository.findAll();
    }

    public Film create(Film film){
        return filmRepository.save(film);
    }
}
