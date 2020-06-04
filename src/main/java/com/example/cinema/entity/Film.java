package com.example.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "FILMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Positive
    @Min(1)
    private int runningTimeInMinutes;
    private String country;
    @Positive
    @Min(1950)
    private int releaseYear;
    private String teaserUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<FilmGenre> genres;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Actor> actors;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.PERSIST,
            orphanRemoval = true, mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
        tickets.forEach(ticket -> ticket.setFilm(this));
    }

    @JsonIgnore
    public List<Ticket> getAvailableTickets() {
        if (tickets == null) {
            return Collections.emptyList();
        }
        return tickets.stream()
                .filter(t -> t.getPurchaseTime() == null)
                .sorted(Comparator.comparing(Ticket::getPlaceNumber))
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<AvailableTicketsByDate> getTicketsByDate() {
        if (tickets == null) {
            return Collections.emptyList();
        }
        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getFilmStartTime))
                .entrySet()
                .stream()
                .map(entry -> new AvailableTicketsByDate(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(AvailableTicketsByDate::getTime))
                .collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
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
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
