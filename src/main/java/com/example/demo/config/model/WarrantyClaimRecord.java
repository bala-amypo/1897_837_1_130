package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String claimantName;
    private String claimantEmail;
    private String claimReason;
    private String status = "PENDING";
    private LocalDateTime submittedAt;

    @ManyToOne
    private DeviceOwnershipRecord device;

    @PrePersist
    void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    public String getSerialNumber() { return serialNumber; }
    public String getClaimReason() { return claimReason; }
    public void setStatus(String status) { this.status = status; }
    public void setDevice(DeviceOwnershipRecord device) { this.device = device; }
}
