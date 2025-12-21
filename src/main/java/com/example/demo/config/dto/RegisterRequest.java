package com.example.demo.dto;

import java.util.Set;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Set<String> getRoles() { return roles; }
}
