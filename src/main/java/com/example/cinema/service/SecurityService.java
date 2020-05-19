package com.example.cinema.service;

import com.example.cinema.dto.SessionDto;
import com.example.cinema.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public SecurityService(AuthenticationManager authenticationManager,
            @Qualifier("userDetailsServiceImpl")
                    UserDetailsService userDetailsService,
            UserService userService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    public SessionDto getCurrentSession() {
        SessionDto sessionDto = new SessionDto();
        User currentUser = findLoggedInUser();

        if (currentUser != null) {
            sessionDto.setUser(currentUser);
            sessionDto.setAuthenticated(true);
        }
        return sessionDto;
    }

    public User findLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String name = ((UserDetails) userDetails).getUsername();
            return userService.findByName(name);
        }

        return null;
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            log.debug("Auto login {} successfully!", username);
        }
    }
}
