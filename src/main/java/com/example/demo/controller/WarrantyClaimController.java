package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService claimService;

    public WarrantyClaimController(WarrantyClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<?> submitClaim(@RequestBody WarrantyClaimRecord claim) {
        return ResponseEntity.ok(claimService.submitClaim(claim));
    }

    @GetMapping
    public ResponseEntity<?> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClaimById(@PathVariable Long id) {
        return ResponseEntity.of(claimService.getClaimById(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getClaimsBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(claimService.getClaimsBySerial(serialNumber));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateClaimStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(claimService.updateClaimStatus(id, status));
    }
}