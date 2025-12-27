package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService deviceService;

    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<?> registerDevice(@RequestBody DeviceOwnershipRecord device) {
        return ResponseEntity.ok(deviceService.registerDevice(device));
    }

    @GetMapping
    public ResponseEntity<?> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        // Service methods usually return Optional, strictly getting value or letting exception handler catch it
        // Assuming service layer throws ResourceNotFoundException or we map here.
        // Based on implementation, repo.findById returns Optional.
        // We will assume standard behavior:
        return ResponseEntity.ok(deviceService.getAllDevices().stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElseThrow()); 
                // In production, use service.getById(id)
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getDeviceBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.of(deviceService.getBySerial(serialNumber));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateDeviceStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, active));
    }
}