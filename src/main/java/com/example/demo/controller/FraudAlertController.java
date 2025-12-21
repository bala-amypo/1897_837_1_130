package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService service;

    public FraudAlertController(FraudAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FraudAlertRecord> create(
            @RequestBody FraudAlertRecord alert
    ) {
        return new ResponseEntity<>(
                service.create(alert),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolve(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.resolve(id));
    }

    @GetMapping
    public ResponseEntity<List<FraudAlertRecord>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudAlertRecord> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<FraudAlertRecord>> getBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(service.getBySerial(serialNumber));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<FraudAlertRecord>> getByClaim(
            @PathVariable Long claimId
    ) {
        return ResponseEntity.ok(service.getByClaim(claimId));
    }
}
