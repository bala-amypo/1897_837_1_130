package com.example.demo.service.impl;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.WarrantyClaimRecordRepository;
import com.example.demo.service.WarrantyClaimService;

import java.util.List;
import java.util.Optional;

public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepository;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepository
    ) {
        this.claimRepository = claimRepository;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        boolean duplicate =
                claimRepository.existsBySerialNumberAndClaimReason(
                        claim.getSerialNumber(),
                        claim.getClaimReason()
                );

        if (duplicate) {
            claim.setStatus("FLAGGED");
        }

        return claimRepository.save(claim);
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long id, String status) {

        WarrantyClaimRecord claim =
                claimRepository.findById(id)
                        .orElseThrow(() ->
                                new java.util.NoSuchElementException(
                                        "Request not found"
                                )
                        );

        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    @Override
    public Optional<WarrantyClaimRecord> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber) {
        return claimRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepository.findAll();
    }
}
