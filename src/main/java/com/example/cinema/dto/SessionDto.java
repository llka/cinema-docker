package com.example.cinema.dto;

import com.example.cinema.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private boolean authenticated = false;
    private User user;
}
