package com.example.cinema.mapper;

import com.example.cinema.dto.FilmGenreDto;
import com.example.cinema.entity.FilmGenre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FilmGenreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "films", ignore = true)
    FilmGenre toEntity(FilmGenreDto dto);
}
