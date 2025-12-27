package com.example.demo.service;

import java.util.List;

import com.example.demo.model.FraudAlertRecord;

public interface FraudAlertService {

    FraudAlertRecord createAlert(FraudAlertRecord alert);

    FraudAlertRecord resolveAlert(Long id);

    List<FraudAlertRecord> getAlertsByClaim(Long claimId);
}