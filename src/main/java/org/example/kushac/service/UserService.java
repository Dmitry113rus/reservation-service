package org.example.kushac.service;

import lombok.RequiredArgsConstructor;
import org.example.kushac.dto.UserRegistrationDto;
import org.example.kushac.entity.UserEntity;
import org.example.kushac.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    private final PasswordEncoder encoder;

    public boolean isLoginUnique(String username) {
        return repo.findByUsername(username).isEmpty();
    }

    public UserEntity register(UserRegistrationDto dto) {
        if (repo.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Логин уже занят");
        }

        UserEntity u = new UserEntity();

        u.setFirstName(dto.getFirstName());
        u.setLastName(dto.getLastName());
        u.setPhone(dto.getPhone());
        u.setEmail(dto.getEmail());
        u.setUsername(dto.getUsername());
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setRole("USER");

        return repo.save(u);
    }
}

