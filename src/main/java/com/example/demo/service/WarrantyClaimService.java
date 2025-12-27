package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.WarrantyClaimRecord;

public interface WarrantyClaimService {

    WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim);

    WarrantyClaimRecord updateClaimStatus(Long id, String status);

    Optional<WarrantyClaimRecord> getClaimById(Long id);

    List<WarrantyClaimRecord> getAllClaims();

    List<WarrantyClaimRecord> getClaimsBySerial(String serial);
}