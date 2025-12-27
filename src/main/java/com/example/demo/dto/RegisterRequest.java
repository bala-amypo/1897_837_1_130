package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema; // Import Schema
import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;

    @Schema(hidden = true) // <--- This HIDES it from the Swagger input list
    private Set<String> roles;
}