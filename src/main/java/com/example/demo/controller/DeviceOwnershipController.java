package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService service;

    public DeviceOwnershipController(DeviceOwnershipService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> register(
            @RequestBody DeviceOwnershipRecord device
    ) {
        return new ResponseEntity<>(
                service.register(device),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<DeviceOwnershipRecord>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceOwnershipRecord> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<DeviceOwnershipRecord> getBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(service.getBySerial(serialNumber));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok(
                service.updateStatus(id, active)
        );
    }
}
