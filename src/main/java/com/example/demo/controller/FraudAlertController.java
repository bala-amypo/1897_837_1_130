package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService alertService;

    public FraudAlertController(FraudAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<?> createAlert(
            @RequestBody FraudAlertRecord alert
    ) {
        return ResponseEntity.status(201)
                .body(alertService.createAlert(alert));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolveAlert(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<?> getAlertsByClaim(
            @PathVariable Long claimId
    ) {
        return ResponseEntity.ok(
                alertService.getAlertsByClaim(claimId)
        );
    }
}