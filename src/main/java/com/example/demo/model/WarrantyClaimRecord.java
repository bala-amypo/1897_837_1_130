package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String claimReason;
    private String status = "PENDING";

    /* ===== BUILDER ===== */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final WarrantyClaimRecord c = new WarrantyClaimRecord();

        public Builder id(Long id) { c.id = id; return this; }
        public Builder serialNumber(String s) { c.serialNumber = s; return this; }
        public Builder claimReason(String r) { c.claimReason = r; return this; }
        public Builder status(String s) { c.status = s; return this; }

        public WarrantyClaimRecord build() { return c; }
    }

    /* ===== GETTERS / SETTERS ===== */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public String getClaimReason() { return claimReason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}