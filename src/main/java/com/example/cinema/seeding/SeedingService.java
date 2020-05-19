package com.example.cinema.seeding;

import com.example.cinema.entity.UserRole;
import com.example.cinema.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedingService {

    @Autowired
    public SeedingService(UserRoleRepository userRoleRepository) {

        userRoleRepository.saveAll(userRoles());
    }

    private List<UserRole> userRoles() {
        UserRole role = new UserRole();
        role.setName("client");
        return List.of(role);
    }
}
