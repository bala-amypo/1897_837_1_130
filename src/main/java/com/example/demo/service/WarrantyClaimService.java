package com.example.demo.service;

import com.example.demo.model.WarrantyClaimRecord;

import java.util.List;

public interface WarrantyClaimService {

    WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim);

    WarrantyClaimRecord updateStatus(Long id, String status);

    WarrantyClaimRecord getById(Long id);

    List<WarrantyClaimRecord> getBySerial(String serialNumber);

    List<WarrantyClaimRecord> getAll();
}
