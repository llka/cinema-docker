package com.example.cinema.entity;

import com.example.cinema.util.InstantFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTicketsByDate {
    private Instant time;
    private List<Ticket> tickets;

    public List<Ticket> getAvailableTickets() {
        if (tickets == null) {
            return Collections.emptyList();
        }
        return tickets.stream()
                .filter(t -> t.getPurchaseTime() == null)
                .sorted(Comparator.comparing(Ticket::getPlaceNumber))
                .collect(Collectors.toList());
    }

    public String getFormattedTime() {
        return InstantFormatter.format(time);
    }

    public long getElementId(){
        return time.toEpochMilli();
    }
}
