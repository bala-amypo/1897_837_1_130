package com.example.demo.service;

import com.example.demo.model.StolenDeviceReport;

import java.util.List;

public interface StolenDeviceService {

    StolenDeviceReport report(StolenDeviceReport report);

    StolenDeviceReport getById(Long id);

    StolenDeviceReport getBySerial(String serialNumber);

    List<StolenDeviceReport> getAll();
}
