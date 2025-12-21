package com.example.demo.service.impl;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User registerUser(RegisterRequest r) {
        if (repo.existsByEmail(r.getEmail()))
            throw new IllegalArgumentException("Email already in use");

        return repo.save(new User(
            r.getName(),
            r.getEmail(),
            encoder.encode(r.getPassword()),
            r.getRoles() == null ? Set.of("USER") : r.getRoles()
        ));
    }

    public User loginUser(LoginRequest r) {
        User u = repo.findByEmail(r.getEmail())
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!encoder.matches(r.getPassword(), u.getPassword()))
            throw new IllegalArgumentException("Invalid input");

        return u;
    }
}
