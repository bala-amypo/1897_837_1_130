package com.example.demo.controller;
import com.example.demo.service.impl.WarrantyClaimServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/claims")
public class WarrantyClaimController {
    private final WarrantyClaimServiceImpl service;
    public WarrantyClaimController(WarrantyClaimServiceImpl service) { this.service = service; }
}