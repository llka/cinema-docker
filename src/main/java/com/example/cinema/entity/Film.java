package com.example.cinema.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "FILMS")
public class Film {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "film", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public Film() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Ticket> getAvailableTickets() {
        if(tickets == null){
            return Collections.emptyList();
        }
        return tickets.stream()
                .filter(t -> t.getPurchaseTime() == null)
                .sorted(Comparator.comparing(Ticket::getPlaceNumber))
                .collect(Collectors.toList());
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return Objects.equals(id, film.id) &&
                Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
