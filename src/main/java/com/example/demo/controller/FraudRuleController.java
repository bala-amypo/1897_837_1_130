package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService service;

    public FraudRuleController(FraudRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FraudRule> create(
            @RequestBody FraudRule rule
    ) {
        return new ResponseEntity<>(
                service.create(rule),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraudRule> update(
            @PathVariable Long id,
            @RequestBody FraudRule rule
    ) {
        return ResponseEntity.ok(
                service.update(id, rule)
        );
    }

    @GetMapping
    public ResponseEntity<List<FraudRule>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<FraudRule>> getActive() {
        return ResponseEntity.ok(service.getActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudRule> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }
}
