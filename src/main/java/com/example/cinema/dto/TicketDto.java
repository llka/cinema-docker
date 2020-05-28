package com.example.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    @Positive(message = "placeNumber must be a positive number")
    private Integer placeNumber;
    @NotNull(message = "filmStartTime must be not null")
    private Instant filmStartTime;
    @NotNull(message = "priceInUSD must be not null")
    private BigDecimal priceInUSD;
}
