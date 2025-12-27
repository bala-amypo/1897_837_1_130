package com.example.demo.controller;
import com.example.demo.service.impl.DeviceOwnershipServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/devices")
public class DeviceOwnershipController {
    private final DeviceOwnershipServiceImpl service;
    public DeviceOwnershipController(DeviceOwnershipServiceImpl service) { this.service = service; }
    // Implement standard endpoints matching service methods if needed for manual testing
}