package com.example.cinema.mapper;

import com.example.cinema.dto.FilmDto;
import com.example.cinema.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ActorMapper.class, FilmGenreMapper.class, TicketMapper.class})
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    Film toEntity(FilmDto dto);
}
