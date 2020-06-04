package com.example.cinema.entity;

import com.example.cinema.util.InstantFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "TICKETS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer placeNumber;
    private Instant filmStartTime;
    private Instant purchaseTime;
    @Column(name = "price_in_usd")
    private BigDecimal priceInUSD;

    private boolean booked;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public String getFormattedFilmStartTime() {
        return InstantFormatter.format(filmStartTime);
    }

    public String getFormattedPurchaseTime() {
        if (purchaseTime == null) {
            return "";
        }
        return InstantFormatter.format(purchaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeNumber, filmStartTime, purchaseTime);
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
                Objects.equals(purchaseTime, ticket.purchaseTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", placeNumber=" + placeNumber +
                ", filmStartTime=" + filmStartTime +
                ", purchaseTime=" + purchaseTime +
                ", priceInUSD=" + priceInUSD +
                ", film=" + film +
                '}';
    }
}
