package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.StolenDeviceService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {

    private final StolenDeviceReportRepository stolenRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;

    public StolenDeviceServiceImpl(
            StolenDeviceReportRepository stolenRepository,
            DeviceOwnershipRecordRepository deviceRepository
    ) {
        this.stolenRepository = stolenRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public StolenDeviceReport report(StolenDeviceReport report) {

        if (!deviceRepository.existsBySerialNumber(report.getSerialNumber())) {
            throw new ResourceNotFoundException("Device not found");
        }

        if (stolenRepository.existsBySerialNumber(report.getSerialNumber())) {
            throw new BadRequestException("Invalid input");
        }

        return stolenRepository.save(report);
    }

    @Override
    public StolenDeviceReport getById(Long id) {
        return stolenRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found")
                );
    }

    @Override
    public StolenDeviceReport getBySerial(String serialNumber) {
        return stolenRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found")
                );
    }

    @Override
    public List<StolenDeviceReport> getAll() {
        return stolenRepository.findAll();
    }
}
