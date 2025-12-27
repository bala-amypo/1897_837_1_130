package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber; // Foreign key logic handled in service, stored as String here per DTO flow

    private String claimantName;
    private String claimantEmail;
    private String claimReason;

    private LocalDateTime submittedAt;

    @Builder.Default
    private String status = "PENDING";

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.submittedAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDING";
    }
}