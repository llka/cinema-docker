package com.example.cinema.service;

import com.example.cinema.entity.Film;
import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.User;
import com.example.cinema.exception.RestException;
import com.example.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final UserService userService;
    private final SecurityService securityService;
    private final FilmService filmService;
    private final TicketRepository ticketRepository;

    @Transactional
    public void buyTicket(Long filmId, Long ticketId) {
        Film film = filmService.findFilmById(filmId);

        Ticket ticket = film.getAvailableTickets().stream()
                .filter(t -> t.getId().equals(ticketId))
                .findFirst()
                .orElseThrow(() -> new RestException("No tickets left!", HttpStatus.NOT_FOUND));

        User user = securityService.getCurrentUser();
        Set<Ticket> userTickets = user.getTickets();
        if (userTickets == null) {
            userTickets = new HashSet<>();
        }
        userTickets.add(ticket);
        ticket.setPurchaseTime(Instant.now().atOffset(ZoneOffset.ofHours(3)).toInstant());

        userService.save(user);
    }

    @Transactional
    public void removeTicketFromCart(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RestException("Ticket not found by id: " + ticketId, HttpStatus.NOT_FOUND));

        User user = securityService.getCurrentUser();
        Set<Ticket> userTickets = user.getTickets();

        if (userTickets != null && userTickets.contains(ticket)) {
            userTickets.remove(ticket);
            ticket.setPurchaseTime(null);
            ticketRepository.save(ticket);
            userService.save(user);
        }
    }

    @Transactional
    public void bookTicketFromCart(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RestException("Ticket not found by id: " + ticketId, HttpStatus.NOT_FOUND));

        User user = securityService.getCurrentUser();
        Set<Ticket> userTickets = user.getTickets();

        if (userTickets != null && userTickets.contains(ticket)) {
            userTickets.remove(ticket);
            ticket.setBooked(true);
            ticketRepository.save(ticket);
            userService.save(user);
        }
    }
}
