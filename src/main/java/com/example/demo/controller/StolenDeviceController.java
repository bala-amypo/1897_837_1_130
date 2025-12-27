package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService stolenService;

    public StolenDeviceController(StolenDeviceService stolenService) {
        this.stolenService = stolenService;
    }

    @PostMapping
    public ResponseEntity<?> reportStolen(
            @RequestBody StolenDeviceReport report
    ) {
        return ResponseEntity.status(201)
                .body(stolenService.reportStolen(report));
    }

    @GetMapping
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(stolenService.getAllReports());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getReportsBySerial(
            @PathVariable String serial
    ) {
        return ResponseEntity.ok(
                stolenService.getReportsBySerial(serial)
        );
    }
}