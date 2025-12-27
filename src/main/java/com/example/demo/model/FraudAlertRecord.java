package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FraudAlertRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;
    private String serialNumber;
    private String alertType;
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    private String message;
    private LocalDateTime alertDate;
    
    @Builder.Default
    private Boolean resolved = false;

    @PrePersist
    protected void onCreate() { this.alertDate = LocalDateTime.now(); }
}