package com.example.cinema.seeding;

import com.example.cinema.entity.Film;
import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.UserRole;
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

@Service
public class SeedingService {

    @Autowired
    public SeedingService(UserRoleRepository userRoleRepository,
            FilmRepository filmRepository,
            TicketRepository ticketRepository) {

        userRoleRepository.saveAll(userRoles());

        filmRepository.save(film());

        filmRepository.findAll().stream()
                .forEach(film -> ticketRepository.saveAll(tickets(film)));
    }

    private List<UserRole> userRoles() {
        UserRole role = new UserRole();
        role.setName("client");
        return List.of(role);
    }

    private Film film() {
        Film film = new Film();
        film.setTitle("Avengers. Final ");
        return film;
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
