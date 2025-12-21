package com.example.demo.service;

import com.example.demo.model.FraudAlertRecord;

import java.util.List;

public interface FraudAlertService {

    FraudAlertRecord create(FraudAlertRecord alert);

    FraudAlertRecord resolve(Long id);

    FraudAlertRecord getById(Long id);

    List<FraudAlertRecord> getBySerial(String serialNumber);

    List<FraudAlertRecord> getByClaim(Long claimId);

    List<FraudAlertRecord> getAll();
}
