package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FraudAlertServiceImpl {
    private final FraudAlertRecordRepository repo;

    public FraudAlertServiceImpl(FraudAlertRecordRepository repo) { this.repo = repo; }

    public FraudAlertRecord createAlert(FraudAlertRecord alert) { return repo.save(alert); }

    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord alert = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Alert not found"));
        alert.setResolved(true);
        return repo.save(alert);
    }

    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) { return repo.findByClaimId(claimId); }
}