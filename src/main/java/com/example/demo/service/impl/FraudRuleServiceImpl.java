package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudAlertServiceImpl implements FraudAlertService {

    private final FraudAlertRecordRepository repository;

    public FraudAlertServiceImpl(FraudAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public FraudAlertRecord create(FraudAlertRecord alert) {
        return repository.save(alert);
    }

    @Override
    public FraudAlertRecord resolve(Long id) {

        FraudAlertRecord alert = getById(id);
        alert.setResolved(true);

        return repository.save(alert);
    }

    @Override
    public FraudAlertRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found")
                );
    }

    @Override
    public List<FraudAlertRecord> getBySerial(String serialNumber) {
        return repository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<FraudAlertRecord> getByClaim(Long claimId) {
        return repository.findByClaimId(claimId);
    }

    @Override
    public List<FraudAlertRecord> getAll() {
        return repository.findAll();
    }
}
