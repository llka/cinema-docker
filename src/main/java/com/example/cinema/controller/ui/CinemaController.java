package com.example.cinema.controller.ui;

import com.example.cinema.service.SecurityService;
import com.example.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Slf4j
@Controller
@RequestMapping(path = "/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping
    public String start(final Model model) {

        model.addAttribute("session", securityService.getCurrentSession());

        return "start";
    }
}
