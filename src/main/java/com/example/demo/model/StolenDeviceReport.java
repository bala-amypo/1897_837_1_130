package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "stolen_device_reports")
public class StolenDeviceReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    private String reportedBy;
    private String details;
    private LocalDateTime reportDate;

    @PrePersist
    protected void onCreate() {
        this.reportDate = LocalDateTime.now();
    }
}