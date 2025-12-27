package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService stolenService;

    public StolenDeviceController(StolenDeviceService stolenService) {
        this.stolenService = stolenService;
    }

    @PostMapping
    public ResponseEntity<?> reportStolen(@RequestBody StolenDeviceReport report) {
        return ResponseEntity.ok(stolenService.reportStolen(report));
    }

    @GetMapping
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(stolenService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        return ResponseEntity.of(stolenService.getReportById(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getReportsBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(stolenService.getReportsBySerial(serialNumber));
    }
}