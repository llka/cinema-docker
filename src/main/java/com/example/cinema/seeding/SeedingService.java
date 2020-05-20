package com.example.cinema.seeding;

import com.example.cinema.entity.Actor;
import com.example.cinema.entity.Film;
import com.example.cinema.entity.FilmGenre;
import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.UserRole;
import com.example.cinema.repository.ActorRepository;
import com.example.cinema.repository.FilmGenreRepository;
import com.example.cinema.repository.FilmRepository;
import com.example.cinema.repository.TicketRepository;
import com.example.cinema.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SeedingService {

    @Autowired
    public SeedingService(UserRoleRepository userRoleRepository,
            FilmRepository filmRepository,
            TicketRepository ticketRepository,
            FilmGenreRepository genreRepository,
            ActorRepository actorRepository) {

        userRoleRepository.saveAll(userRoles());

        genreRepository.saveAll(genres());

        filmRepository.save(film(genreRepository.findByName("comedy")));

        filmRepository.findAll().stream()
                .forEach(film -> ticketRepository.saveAll(tickets(film)));

        actorRepository.saveAll(actors(filmRepository.findAll().stream().findFirst().orElseThrow()));
    }

    private List<UserRole> userRoles() {
        UserRole role = new UserRole();
        role.setName("client");
        return List.of(role);
    }

    private Film film(FilmGenre genre) {
        Film film = new Film();
        film.setTitle("Avengers. Final ");
        film.setRunningTimeInMinutes(120);
        film.setCountry("USA");
        film.setReleaseYear(2019);
        film.setGenres(Set.of(genre));
        return film;
    }

    private List<Actor> actors(Film film) {
        Actor a = new Actor();
        a.setFirstName("Vin");
        a.setLastName("Diesel");
        a.setBirthYear(1970);
        a.setFilms(Set.of(film));

        Actor b = new Actor();
        b.setFirstName("Kurt");
        b.setLastName("Russell");
        b.setBirthYear(1972);
        b.setFilms(Set.of(film));

        return List.of(a, b);

    }

    private List<FilmGenre> genres() {
        FilmGenre comedy = new FilmGenre();
        comedy.setName("comedy");

        FilmGenre drama = new FilmGenre();
        drama.setName("drama");

        return List.of(comedy, drama);
    }

    private List<Ticket> tickets(Film film) {
        List<Ticket> tickets = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket();
            ticket.setPlaceNumber(i + 1);
            ticket.setFilmStartTime(Instant.now().plus(10, ChronoUnit.HOURS));
            ticket.setFilm(film);
            ticket.setPriceInUSD(BigDecimal.valueOf(5.50));
            tickets.add(ticket);
        }
        return tickets;
    }
}
