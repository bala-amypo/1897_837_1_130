package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.service.StolenDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {

    private final StolenDeviceReportRepository stolenRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;

    public StolenDeviceServiceImpl(StolenDeviceReportRepository stolenRepository, DeviceOwnershipRecordRepository deviceRepository) {
        this.stolenRepository = stolenRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        // Test 20 expects NoSuchElementException if device is missing
        if (!deviceRepository.findBySerialNumber(report.getSerialNumber()).isPresent()) {
            throw new NoSuchElementException("Device not found"); // [cite: 96]
        }
        return stolenRepository.save(report);
    }

    @Override
    public List<StolenDeviceReport> getReportsBySerial(String serialNumber) {
        return stolenRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public Optional<StolenDeviceReport> getReportById(Long id) {
        return stolenRepository.findById(id);
    }

    @Override
    public List<StolenDeviceReport> getAllReports() {
        return stolenRepository.findAll();
    }
}