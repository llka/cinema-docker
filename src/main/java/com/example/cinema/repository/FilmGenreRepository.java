package com.example.cinema.repository;

import com.example.cinema.entity.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {
    FilmGenre findByName(String name);
}
