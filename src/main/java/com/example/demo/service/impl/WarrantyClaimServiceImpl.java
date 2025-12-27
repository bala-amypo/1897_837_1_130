package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.repository.WarrantyClaimRecordRepository;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;
    private final StolenDeviceReportRepository stolenRepository;
    private final FraudAlertRecordRepository alertRepository;
    private final FraudRuleRepository ruleRepository;

    public WarrantyClaimServiceImpl(WarrantyClaimRecordRepository claimRepository,
                                    DeviceOwnershipRecordRepository deviceRepository,
                                    StolenDeviceReportRepository stolenRepository,
                                    FraudAlertRecordRepository alertRepository,
                                    FraudRuleRepository ruleRepository) {
        this.claimRepository = claimRepository;
        this.deviceRepository = deviceRepository;
        this.stolenRepository = stolenRepository;
        this.alertRepository = alertRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {
        // 1. Check if device exists (Test 12)
        DeviceOwnershipRecord device = deviceRepository.findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found")); // [cite: 216]

        String status = "PENDING";

        // 2. Check duplicate claim (Test 13)
        if (claimRepository.existsBySerialNumberAndClaimReason(claim.getSerialNumber(), claim.getClaimReason())) {
            status = "FLAGGED"; // [cite: 82]
        }

        // 3. Check expired warranty (Test 14)
        if (device.getWarrantyExpiration() != null && device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            status = "FLAGGED"; // [cite: 83]
        }

        // 4. Check stolen report (Test 15)
        if (stolenRepository.existsBySerialNumber(claim.getSerialNumber())) {
            status = "FLAGGED"; // [cite: 84]
        }

        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long claimId, String status) {
        WarrantyClaimRecord claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));
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