package com.example.demo.controller;

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
    public ResponseEntity<?> registerDevice(
            @RequestBody DeviceOwnershipRecord record
    ) {
        return ResponseEntity.status(201)
                .body(deviceService.registerDevice(record));
    }

    @GetMapping
    public ResponseEntity<?> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> updateDeviceStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok(
                deviceService.updateDeviceStatus(id, active)
        );
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getBySerial(
            @PathVariable String serial
    ) {
        return ResponseEntity.ok(deviceService.getBySerial(serial));
    }
}