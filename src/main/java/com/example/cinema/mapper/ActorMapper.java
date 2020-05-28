package com.example.cinema.mapper;

import com.example.cinema.dto.ActorDto;
import com.example.cinema.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ActorMapper {

    @Mapping(target = "id", ignore=true)
    @Mapping(target = "films", ignore=true)
    Actor toEntity (ActorDto dto);
}
