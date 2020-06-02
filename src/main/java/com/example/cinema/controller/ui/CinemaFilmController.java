package com.example.cinema.controller.ui;

import com.example.cinema.entity.Film;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Slf4j
@Controller
@RequestMapping(path = "/cinema")
@RequiredArgsConstructor
public class CinemaFilmController {
    private final SecurityService securityService;
    private final FilmService filmService;

    @GetMapping("/film")
    public String getFilm(final Model model) {
        Film film = filmService.getAvengersFilm();

        model.addAttribute("film", film);

        model.addAttribute("clientSession", securityService.getCurrentSession());
        return "film";
    }

    @GetMapping("/film/{filmId}")
    public String getFilm(@PathVariable("filmId") Long filmId, final Model model) {
        Film film = filmService.findFilmById(filmId);

        model.addAttribute("film", film);

        model.addAttribute("clientSession", securityService.getCurrentSession());
        return "film";
    }

    @GetMapping("/film/search")
    public String searchFilms(@RequestParam("search") String search, final Model model) {
        Film film = filmService.findFilmByTitle(search);
        model.addAttribute("film", film);

        model.addAttribute("clientSession", securityService.getCurrentSession());
        return "film";
    }
}
