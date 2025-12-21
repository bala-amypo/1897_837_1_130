package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService claimService;

    public WarrantyClaimController(
            WarrantyClaimService claimService
    ) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<WarrantyClaimRecord> submitClaim(
            @RequestBody WarrantyClaimRecord claim
    ) {
        return new ResponseEntity<>(
                claimService.submitClaim(claim),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<WarrantyClaimRecord>> getAllClaims() {
        return ResponseEntity.ok(
                claimService.getAllClaims()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarrantyClaimRecord> getClaimById(
            @PathVariable Long id
    ) {
        return claimService.getClaimById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new java.util.NoSuchElementException(
                                "Request not found"
                        )
                );
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<WarrantyClaimRecord>> getBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(
                claimService.getClaimsBySerial(serialNumber)
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<WarrantyClaimRecord> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(
                claimService.updateClaimStatus(id, status)
        );
    }
}
