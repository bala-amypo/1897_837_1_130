package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "fraud_alert_records")
public class FraudAlertRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;
    private String serialNumber;
    private String alertType;
    private String severity;
    private String message;

    private LocalDateTime alertDate;

    @Builder.Default
    private Boolean resolved = false;

    @PrePersist
    protected void onCreate() {
        this.alertDate = LocalDateTime.now();
        if (this.resolved == null) this.resolved = false;
    }
}