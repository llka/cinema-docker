package com.example.cinema.controller.ui;

import com.example.cinema.service.SecurityService;
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
public class CinemaViewController {
    private final SecurityService securityService;

    @GetMapping
    public String getStartView(final Model model) {
        addClientSessionAttribute(model);
        return "start";
    }

    @GetMapping("/cart")
    public String getCartView(final Model model) {
        addClientSessionAttribute(model);
        return "cart";
    }

    @GetMapping("/sale")
    public String getSalesView(final Model model){
        addClientSessionAttribute(model);
        return "sale";
    }

    @GetMapping("/current_films")
    public String getCurrentFilmsView(final Model model){
        addClientSessionAttribute(model);
        return "current_films";
    }

    @GetMapping("/coming_soon")
    public String getComingSoonView(final Model model){
        addClientSessionAttribute(model);
        return "coming_soon";
    }

    @GetMapping("/about")
    public String getAboutView(final Model model){
        addClientSessionAttribute(model);
        return "about";
    }

    @GetMapping("/feedback")
    public String getFeedbackView(final Model model){
        addClientSessionAttribute(model);
        return "feedback";
    }

    @GetMapping("/contact_us")
    public String getContactUsView(final Model model){
        addClientSessionAttribute(model);
        return "contact_us";
    }

    private void addClientSessionAttribute(final Model model) {
        model.addAttribute("clientSession", securityService.getCurrentSession());
    }
}
