package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);

    Optional<User> findByEmail(String email);
}