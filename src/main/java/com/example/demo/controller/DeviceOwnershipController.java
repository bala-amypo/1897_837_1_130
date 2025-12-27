package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService deviceService;

    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> registerDevice(
            @RequestBody DeviceOwnershipRecord record) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.registerDevice(record));
    }

    @GetMapping
    public ResponseEntity<List<DeviceOwnershipRecord>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    // ✅ UPDATE STATUS (MUST BE PUT, NOT GET)
    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateDeviceStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                deviceService.updateDeviceStatus(id, active)
        );
    }

    // ✅ GET BY SERIAL (NO OPTIONAL LEAK)
    @GetMapping("/serial/{serial}")
    public ResponseEntity<DeviceOwnershipRecord> getBySerial(
            @PathVariable String serial) {

        return deviceService.getBySerial(serial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

