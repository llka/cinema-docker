package com.example.cinema.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private Integer placeNumber;
    private Instant filmStartTime;
    private Instant purchaseTime;
    @Column(name = "price_in_usd")
    private BigDecimal priceInUSD;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Instant getFilmStartTime() {
        return filmStartTime;
    }

    public void setFilmStartTime(Instant filmStartTime) {
        this.filmStartTime = filmStartTime;
    }

    public Instant getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Instant purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public BigDecimal getPriceInUSD() {
        return priceInUSD;
    }

    public void setPriceInUSD(BigDecimal priceInUSD) {
        this.priceInUSD = priceInUSD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(placeNumber, ticket.placeNumber) &&
                Objects.equals(filmStartTime, ticket.filmStartTime) &&
                Objects.equals(purchaseTime, ticket.purchaseTime) &&
                Objects.equals(film, ticket.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeNumber, filmStartTime, purchaseTime, film);
    }
}
