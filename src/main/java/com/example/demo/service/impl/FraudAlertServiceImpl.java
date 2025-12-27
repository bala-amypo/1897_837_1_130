package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;

@Service
public class FraudAlertServiceImpl implements FraudAlertService {

    private final FraudAlertRecordRepository alertRepo;

    public FraudAlertServiceImpl(FraudAlertRecordRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public FraudAlertRecord createAlert(FraudAlertRecord alert) {
        return alertRepo.save(alert);
    }

    @Override
    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord record = alertRepo.findById(id)
                .orElseThrow(NoSuchElementException::new);
        record.setResolved(true);
        return alertRepo.save(record);
    }

    @Override
    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
        return alertRepo.findByClaimId(claimId);
    }
}