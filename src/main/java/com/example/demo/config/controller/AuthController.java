package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtTokenProvider jwt;

    public AuthController(UserService service, JwtTokenProvider jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest r) {
        return ResponseEntity.ok(service.registerUser(r));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest r) {
        User u = service.loginUser(r);
        String token = jwt.createToken(u.getId(), u.getEmail(), u.getRoles());
        return ResponseEntity.ok(new AuthResponse(token, u.getId(), u.getEmail(), u.getRoles()));
    }
}
