package com.example.cinema.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@ControllerAdvice(basePackages = "com.example.cinema.controller")
public class RestExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(RestException.class)
    public ModelAndView handleRestException(RestException exception) {
        log.info(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.addObject("status", Optional.ofNullable(exception.getStatus())
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR));
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAnyException(Exception exception) {
        log.error("Unexpected exception: {}", exception.getMessage(), exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }
}
