package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarrantyClaimServiceImpl {
    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;

    public WarrantyClaimServiceImpl(WarrantyClaimRecordRepository claimRepo,
                                    DeviceOwnershipRecordRepository deviceRepo,
                                    StolenDeviceReportRepository stolenRepo,
                                    FraudAlertRecordRepository alertRepo,
                                    FraudRuleRepository ruleRepo) {
        this.claimRepo = claimRepo;
        this.deviceRepo = deviceRepo;
        this.stolenRepo = stolenRepo;
        this.alertRepo = alertRepo;
        this.ruleRepo = ruleRepo;
    }

    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {
        DeviceOwnershipRecord device = deviceRepo.findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));

        boolean isDuplicate = claimRepo.existsBySerialNumberAndClaimReason(claim.getSerialNumber(), claim.getClaimReason());
        boolean isStolen = stolenRepo.existsBySerialNumber(claim.getSerialNumber());
        boolean isExpired = device.getWarrantyExpiration().isBefore(LocalDate.now());

        if (isDuplicate || isStolen || isExpired) {
            claim.setStatus("FLAGGED");
        } else {
            claim.setStatus("PENDING");
        }
        
        return claimRepo.save(claim);
    }

    public WarrantyClaimRecord updateClaimStatus(Long id, String status) {
        WarrantyClaimRecord claim = claimRepo.findById(id).orElseThrow();
        claim.setStatus(status);
        return claimRepo.save(claim);
    }

    public Optional<WarrantyClaimRecord> getClaimById(Long id) { return claimRepo.findById(id); }
    public List<WarrantyClaimRecord> getAllClaims() { return claimRepo.findAll(); }
    public List<WarrantyClaimRecord> getClaimsBySerial(String serial) { return claimRepo.findBySerialNumber(serial); }
}