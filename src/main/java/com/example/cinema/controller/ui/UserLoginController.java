package com.example.cinema.controller.ui;

import com.example.cinema.entity.User;
import com.example.cinema.service.SecurityService;
import com.example.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@ApiIgnore
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserLoginController {
    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping("/login")
    public String login(final Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(final Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user, final Model model) {
        String decodedPassword = user.getPassword();
        userService.checkIdUserAlreadyExists(user);
        user = userService.save(user);

        securityService.autoLogin(user.getName(), decodedPassword);
        model.addAttribute("session", securityService.getCurrentSession());
        return "start";
    }
}
