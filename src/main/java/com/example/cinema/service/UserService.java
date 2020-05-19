package com.example.cinema.service;

import com.example.cinema.entity.User;
import com.example.cinema.exception.RestException;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new RestException("User not found by name: " + name, HttpStatus.NOT_FOUND));
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
    }

    public void checkIdUserAlreadyExists(User user) {
        if (user == null) {
            throw new RestException("User info not provided!", HttpStatus.BAD_REQUEST);
        }
        var opt = userRepository.findByName(user.getName());
        if (opt.isPresent()) {
            throw new RestException("User with name: " + user.getName() + " already exits.", HttpStatus.BAD_REQUEST);
        }
    }
}
