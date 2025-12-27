package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class StolenDeviceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final StolenDeviceReport s = new StolenDeviceReport();
        public Builder id(Long id) { s.id = id; return this; }
        public Builder serialNumber(String sn) { s.serialNumber = sn; return this; }
        public StolenDeviceReport build() { return s; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
}