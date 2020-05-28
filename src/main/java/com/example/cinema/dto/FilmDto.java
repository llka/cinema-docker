package com.example.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    @NotBlank(message = "title must be not blank")
    private String title;
    @Min(value = 1, message = "runningTimeInMinutes must be more than 1")
    private int runningTimeInMinutes;
    private String country;
    @Min(value = 1950, message = "runningTimeInMinutes must be more than 1")
    private int releaseYear;
    private String teaserUrl;

    private Set<FilmGenreDto> genres;

    private Set<ActorDto> actors;

    private Set<TicketDto> tickets;
}
