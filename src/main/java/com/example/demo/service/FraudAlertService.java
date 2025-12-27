package com.example.demo.service;

import com.example.demo.model.FraudAlertRecord;
import java.util.List;

public interface FraudAlertService {
    FraudAlertRecord createAlert(FraudAlertRecord alert);
    FraudAlertRecord resolveAlert(Long id);
    List<FraudAlertRecord> getAlertsByClaim(Long claimId);
    // Added missing method
    List<FraudAlertRecord> getAlertsBySerial(String serialNumber);
    List<FraudAlertRecord> getAllAlerts();
}