package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

  // ... (imports remain the same)

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body("Email already in use");
        }
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                // FIX: If roles are null/empty, force "USER"
                .roles(req.getRoles() == null || req.getRoles().isEmpty() ? java.util.Set.of("USER") : req.getRoles())
                .build();
        User saved = userRepo.save(user);
        String token = jwtTokenProvider.createToken(saved.getId(), saved.getEmail(), saved.getRoles());
        return ResponseEntity.ok(new AuthResponse(token, saved.getId(), saved.getEmail(), saved.getRoles()));
    }

// ... (rest of the file remains the same)

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Optional<User> userOp = userRepo.findByEmail(req.getEmail());
        if (userOp.isPresent() && passwordEncoder.matches(req.getPassword(), userOp.get().getPassword())) {
            User u = userOp.get();
            String token = jwtTokenProvider.createToken(u.getId(), u.getEmail(), u.getRoles());
            return ResponseEntity.ok(new AuthResponse(token, u.getId(), u.getEmail(), u.getRoles()));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}