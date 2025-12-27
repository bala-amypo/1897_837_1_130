package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claim_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WarrantyClaimRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String claimantName;
    private String claimantEmail;
    private String claimReason;
    
    private LocalDateTime submittedAt;
    
    @Builder.Default
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED, FLAGGED
    
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { 
        this.createdAt = LocalDateTime.now(); 
        if(this.submittedAt == null) this.submittedAt = LocalDateTime.now();
    }
}