package com.example.cinema.controller.ui;

import com.example.cinema.entity.Film;
import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.User;
import com.example.cinema.exception.RestException;
import com.example.cinema.repository.FilmRepository;
import com.example.cinema.repository.TicketRepository;
import com.example.cinema.service.SecurityService;
import com.example.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ApiIgnore
@Slf4j
@Controller
@RequestMapping(path = "/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final UserService userService;
    private final SecurityService securityService;
    private final FilmRepository filmRepository;
    private final TicketRepository ticketRepository;

    @GetMapping
    public String start(final Model model) {

        model.addAttribute("clientSession", securityService.getCurrentSession());

        return "start";
    }

    @GetMapping("/film")
    public String getFilm(final Model model) {

        Film film = filmRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RestException("No films found", HttpStatus.NOT_FOUND));

        model.addAttribute("clientSession", securityService.getCurrentSession());
        model.addAttribute("film", film);

        return "film";
    }

    @PostMapping("/film/{filmId}/ticket/{ticketId}/buy")
    public String buyRandomTicket(@PathVariable("filmId") Long filmId,
            @PathVariable("ticketId") Long ticketId,
            final Model model) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RestException("Films by id: " + filmId + "not found", HttpStatus.NOT_FOUND));

        Ticket ticket = film.getAvailableTickets().stream()
                .filter(t -> t.getId().equals(ticketId))
                .findFirst()
                .orElseThrow(() -> new RestException("No tickets left", HttpStatus.NOT_FOUND));

        User user = securityService.getCurrentUser();
        Set<Ticket> userTickets = user.getTickets();
        if (userTickets == null) {
            userTickets = new HashSet<>();
        }
        userTickets.add(ticket);
        ticket.setPurchaseTime(Instant.now());

        userService.save(user);

        model.addAttribute("clientSession", securityService.getCurrentSession());

        return "cart";
    }

    @GetMapping("/cart")
    public String getCartView(final Model model) {

        model.addAttribute("clientSession", securityService.getCurrentSession());

        return "cart";
    }

    @GetMapping("/ticket/{id}/removeFromCart")
    public String removeTicketByIdFromCart(@PathVariable("id") Long id, final Model model) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RestException("Ticket not found by id: " + id, HttpStatus.NOT_FOUND));

        User user = securityService.getCurrentUser();
        Set<Ticket> userTickets = user.getTickets();

        if (userTickets != null && userTickets.contains(ticket)) {
            userTickets.remove(ticket);
            ticket.setPurchaseTime(null);
            userService.save(user);
        }

        model.addAttribute("clientSession", securityService.getCurrentSession());
        return "cart";
    }
}
