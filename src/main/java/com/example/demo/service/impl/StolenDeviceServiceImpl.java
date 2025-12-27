package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StolenDeviceServiceImpl {
    private final StolenDeviceReportRepository stolenRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;

    public StolenDeviceServiceImpl(StolenDeviceReportRepository stolenRepo, DeviceOwnershipRecordRepository deviceRepo) {
        this.stolenRepo = stolenRepo;
        this.deviceRepo = deviceRepo;
    }

    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        if (deviceRepo.findBySerialNumber(report.getSerialNumber()).isEmpty()) {
            throw new NoSuchElementException("Device not found");
        }
        return stolenRepo.save(report);
    }

    public List<StolenDeviceReport> getReportsBySerial(String serial) {
        return stolenRepo.findBySerialNumber(serial);
    }
    
    public List<StolenDeviceReport> getAllReports() { return stolenRepo.findAll(); }
}