package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeviceOwnershipServiceImpl {
    private final DeviceOwnershipRecordRepository repo;

    public DeviceOwnershipServiceImpl(DeviceOwnershipRecordRepository repo) {
        this.repo = repo;
    }

    public DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device) {
        if (repo.existsBySerialNumber(device.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number already exists");
        }
        return repo.save(device);
    }

    public Optional<DeviceOwnershipRecord> getBySerial(String serial) {
        return repo.findBySerialNumber(serial);
    }

    public List<DeviceOwnershipRecord> getAllDevices() {
        return repo.findAll();
    }

    public DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active) {
        DeviceOwnershipRecord device = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        device.setActive(active);
        return repo.save(device);
    }
}