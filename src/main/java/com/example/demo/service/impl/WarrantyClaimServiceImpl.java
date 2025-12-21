package com.example.demo.service.impl;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.WarrantyClaimRecordRepository;
import com.example.demo.service.WarrantyClaimService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository repository;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        boolean duplicate =
                repository.existsBySerialNumberAndClaimReason(
                        claim.getSerialNumber(),
                        claim.getClaimReason()
                );

        if (duplicate) {
            claim.setStatus("FLAGGED");
        }

        return repository.save(claim);
    }

    @Override
    public WarrantyClaimRecord updateStatus(Long id, String status) {

        WarrantyClaimRecord claim = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found")
                );

        claim.setStatus(status);
        return repository.save(claim);
    }

    @Override
    public WarrantyClaimRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found")
                );
    }

    @Override
    public List<WarrantyClaimRecord> getBySerial(String serialNumber) {
        return repository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<WarrantyClaimRecord> getAll() {
        return repository.findAll();
    }
}
