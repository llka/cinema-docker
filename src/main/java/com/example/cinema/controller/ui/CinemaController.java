package com.example.cinema.controller.ui;

import com.example.cinema.entity.Film;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.SecurityService;
import com.example.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Slf4j
@Controller
@RequestMapping(path = "/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final SecurityService securityService;
    private final FilmService filmService;
    private final TicketService ticketService;

    @GetMapping
    public String start(final Model model) {
        addClientSessionAttribute(model);
        return "start";
    }

    @GetMapping("/film")
    public String getFilm(final Model model) {
        Film film = filmService.getAvengersFilm();

        model.addAttribute("film", film);

        addClientSessionAttribute(model);
        return "film";
    }

    @GetMapping("/film/{filmId}")
    public String getFilm(@PathVariable("filmId") Long filmId, final Model model) {
        Film film = filmService.findFilmById(filmId);

        model.addAttribute("film", film);

        addClientSessionAttribute(model);
        return "film";
    }

    @GetMapping("/film/search")
    public String searchFilms(@RequestParam("search") String search, final Model model) {
        Film film = filmService.findFilmByTitle(search);
        model.addAttribute("film", film);

        addClientSessionAttribute(model);
        return "film";
    }

    @PostMapping("/film/{filmId}/ticket/{ticketId}/buy")
    public String buyTicketByIdAndFilm(@PathVariable("filmId") Long filmId,
            @PathVariable("ticketId") Long ticketId,
            final Model model) {
        ticketService.buyTicket(filmId, ticketId);

        addClientSessionAttribute(model);
        return "cart";
    }

    @GetMapping("/cart")
    public String getCartView(final Model model) {
        addClientSessionAttribute(model);
        return "cart";
    }

    @GetMapping("/ticket/{ticketId}/removeFromCart")
    public String removeTicketByIdFromCart(@PathVariable("ticketId") Long ticketId, final Model model) {
        ticketService.removeTicketFromCart(ticketId);

        addClientSessionAttribute(model);
        return "cart";
    }

    private void addClientSessionAttribute(final Model model) {
        model.addAttribute("clientSession", securityService.getCurrentSession());
    }
}
