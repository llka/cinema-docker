package com.example.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "FILM_GENRE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmGenre {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Film> films;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmGenre filmGenre = (FilmGenre) o;
        return Objects.equals(id, filmGenre.id) &&
                Objects.equals(name, filmGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FilmGenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
