package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService claimService;

    public WarrantyClaimController(WarrantyClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<?> submitClaim(
            @RequestBody WarrantyClaimRecord claim
    ) {
        return ResponseEntity.status(201)
                .body(claimService.submitClaim(claim));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(
                claimService.updateClaimStatus(id, status)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getClaimsBySerial(
            @PathVariable String serial
    ) {
        return ResponseEntity.ok(
                claimService.getClaimsBySerial(serial)
        );
    }
}