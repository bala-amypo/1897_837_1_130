package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {

    private final DeviceOwnershipRecordRepository repository;

    public DeviceOwnershipServiceImpl(
            DeviceOwnershipRecordRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public DeviceOwnershipRecord register(DeviceOwnershipRecord device) {

        if (repository.existsBySerialNumber(device.getSerialNumber())) {
            throw new BadRequestException("Serial number already exists");
        }

        return repository.save(device);
    }

    @Override
    public DeviceOwnershipRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found")
                );
    }

    @Override
    public DeviceOwnershipRecord getBySerial(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found")
                );
    }

    @Override
    public List<DeviceOwnershipRecord> getAll() {
        return repository.findAll();
    }

    @Override
    public DeviceOwnershipRecord updateStatus(Long id, boolean active) {

        DeviceOwnershipRecord device = getById(id);
        device.setActive(active);
        return repository.save(device);
    }
}
