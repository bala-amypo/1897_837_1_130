package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService service;

    public StolenDeviceController(StolenDeviceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StolenDeviceReport> report(
            @RequestBody StolenDeviceReport report
    ) {
        return new ResponseEntity<>(
                service.report(report),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<StolenDeviceReport>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StolenDeviceReport> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<StolenDeviceReport> getBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(service.getBySerial(serialNumber));
    }
}
