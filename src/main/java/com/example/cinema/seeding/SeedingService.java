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
import com.example.cinema.util.InstantFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
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

        filmRepository.save(filmAvengers(genreRepository.findByName("Комедия").get()));

        filmRepository.save(filmParasite(genreRepository.findByName("Драма").get()));

        filmRepository.findAll()
                .forEach(film -> ticketRepository.saveAll(tickets(film)));

    }

    private List<UserRole> userRoles() {
        UserRole role = new UserRole();
        role.setName("client");
        return List.of(role);
    }

    private Film filmAvengers(FilmGenre genre) {
        Film film = new Film();
        film.setTitle("Мстители. Финал ");
        film.setRunningTimeInMinutes(120);
        film.setCountry("США");
        film.setReleaseYear(2019);
        film.setGenres(Set.of(genre));
        film.setActors(actorsAvengers());
        film.setTeaserUrl("https://www.youtube.com/embed/gbcVZgO4n4E");
        return film;
    }

    private Film filmParasite(FilmGenre genre) {
        Film film = new Film();
        film.setTitle("Паразиты ");
        film.setRunningTimeInMinutes(132);
        film.setCountry("Корея");
        film.setReleaseYear(2019);
        film.setGenres(Set.of(genre));
        film.setActors(actorsParasite());
        film.setTeaserUrl("https://www.youtube.com/embed/5xH0HfJHsaY");
        return film;
    }

    private Set<Actor> actorsAvengers() {
        Actor a = new Actor();
        a.setFirstName("Вин");
        a.setLastName("Дизель");
        a.setBirthYear(1970);

        Actor b = new Actor();
        b.setFirstName("Курт");
        b.setLastName("Рассел");
        b.setBirthYear(1972);

        return new HashSet<>(List.of(a, b));
    }

    private Set<Actor> actorsParasite() {
        Actor a = new Actor();
        a.setFirstName("Квай");
        a.setLastName("Гон");
        a.setBirthYear(1970);

        Actor b = new Actor();
        b.setFirstName("Джей Сон");
        b.setLastName("Парк");
        b.setBirthYear(1972);

        return new HashSet<>(List.of(a, b));
    }

    private List<FilmGenre> genres() {
        FilmGenre comedy = new FilmGenre();
        comedy.setName("Комедия");

        FilmGenre drama = new FilmGenre();
        drama.setName("Драма");

        return List.of(comedy, drama);
    }

    private List<Ticket> tickets(Film film) {
        List<Ticket> tickets = new ArrayList<>(10);

        List<Instant> times = List.of(LocalDate.now().atTime(18, 0).toInstant(InstantFormatter.MINSK_OFFSET),
                LocalDate.now().atTime(21, 0).toInstant(InstantFormatter.MINSK_OFFSET));

        for (int i = 1; i <= 7; i++) {
            for (Instant time : times) {
                Instant filmStartDateTime = time.plus(i, ChronoUnit.DAYS);
                for (int j = 0; j < 10; j++) {
                    Ticket ticket = new Ticket();
                    ticket.setPlaceNumber(j + 1);
                    ticket.setFilmStartTime(filmStartDateTime);
                    ticket.setFilm(film);
                    ticket.setPriceInUSD(BigDecimal.valueOf(5.50));
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }
}
