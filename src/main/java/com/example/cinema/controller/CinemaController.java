package com.example.cinema.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(path = "cinema/")
public class CinemaController {
    @GetMapping
    public String start(final Model model) {
        return "start";
    }
}
