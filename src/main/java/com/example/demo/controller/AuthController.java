package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            UserService userService,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody User user
    ) {
        return new ResponseEntity<>(
                userService.register(user),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        userService.login(email, password);
        String token = jwtTokenProvider.generateToken(email);
        return ResponseEntity.ok(token);
    }
}
