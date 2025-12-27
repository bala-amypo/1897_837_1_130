package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService alertService;

    public FraudAlertController(FraudAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<?> createAlert(@RequestBody FraudAlertRecord alert) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }

    @GetMapping
    public ResponseEntity<?> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertById(@PathVariable Long id) {
        // Simple stream lookup from all alerts as generic placeholder if service method specific isn't exposed
        return ResponseEntity.ok(alertService.getAllAlerts().stream()
                .filter(a -> a.getId().equals(id)).findFirst().orElseThrow());
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getAlertsBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(alertService.getAlertsBySerial(serialNumber));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<?> getAlertsByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(alertService.getAlertsByClaim(claimId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}