package com.example.cinema.service;

import com.example.cinema.entity.Actor;
import com.example.cinema.entity.Film;
import com.example.cinema.entity.FilmGenre;
import com.example.cinema.exception.RestException;
import com.example.cinema.repository.ActorRepository;
import com.example.cinema.repository.FilmGenreRepository;
import com.example.cinema.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final FilmGenreRepository genreRepository;
    private final ActorRepository actorRepository;

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

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film create(Film film) {
        Set<FilmGenre> genres = new HashSet<>();
        film.getGenres().forEach(genre -> {
            var genreOptional = genreRepository.findByName(genre.getName());
            if (genreOptional.isPresent()) {
                genres.add(genreOptional.get());
            }
            else {
                genres.add(genreRepository.save(FilmGenre.builder().name(genre.getName()).build()));
            }
        });
        film.setGenres(genres);

        Set<Actor> actors = new HashSet<>();
        film.getActors().forEach(actor -> {
            var actorOptional = actorRepository.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName());
            if(actorOptional.isPresent()){
                actors.add(actorOptional.get());
            }else {
                actor.setId(null);
                actors.add(actorRepository.save(actor));
            }
        });
        film.setActors(actors);

        return filmRepository.save(film);
    }
}
