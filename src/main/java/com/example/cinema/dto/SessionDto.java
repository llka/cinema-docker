package com.example.cinema.dto;

import com.example.cinema.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private boolean isAuthenticated = false;
    private User user;
}
