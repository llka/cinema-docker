package com.example.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    public int hashCode() {
        return Objects.hash(id, placeNumber, filmStartTime, purchaseTime);
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
