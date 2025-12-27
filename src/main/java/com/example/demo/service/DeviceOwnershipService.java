package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.DeviceOwnershipRecord;

public interface DeviceOwnershipService {

    DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device);

    Optional<DeviceOwnershipRecord> getBySerial(String serial);

    List<DeviceOwnershipRecord> getAllDevices();

    DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active);
}