package com.example.cinema.repository;

import com.example.cinema.entity.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {
    Optional<FilmGenre> findByName(String name);
}
