package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StolenDeviceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String reportedBy;
    private LocalDateTime reportDate;
    private String details;

    @PrePersist
    protected void onCreate() {
        this.reportDate = LocalDateTime.now();
    }
}