package com.example.demo.service;

import java.util.List;

import com.example.demo.model.StolenDeviceReport;

public interface StolenDeviceService {

    StolenDeviceReport reportStolen(StolenDeviceReport report);

    List<StolenDeviceReport> getReportsBySerial(String serial);

    List<StolenDeviceReport> getAllReports();
}