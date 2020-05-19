package com.example.cinema.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public RestException(HttpStatus status) {
        this.status = status;
    }

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public RestException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public RestException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
            HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
