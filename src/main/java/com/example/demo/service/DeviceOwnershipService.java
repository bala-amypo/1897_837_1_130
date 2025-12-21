package com.example.demo.service;

import com.example.demo.model.DeviceOwnershipRecord;

import java.util.List;

public interface DeviceOwnershipService {

    DeviceOwnershipRecord register(DeviceOwnershipRecord device);

    DeviceOwnershipRecord getById(Long id);

    DeviceOwnershipRecord getBySerial(String serialNumber);

    List<DeviceOwnershipRecord> getAll();

    DeviceOwnershipRecord updateStatus(Long id, boolean active);
}
