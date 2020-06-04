package com.example.cinema.mapper;

import com.example.cinema.dto.TicketDto;
import com.example.cinema.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "purchaseTime", ignore = true)
    @Mapping(target = "booked", ignore = true)
    @Mapping(target = "film", ignore = true)
    Ticket toEntity(TicketDto ticketDto);
}
