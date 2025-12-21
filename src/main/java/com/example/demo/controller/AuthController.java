package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<User> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(
                userService.login(email, password)
        );
    }
}
