package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_ownership_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DeviceOwnershipRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String serialNumber;
    private String ownerName;
    private String ownerEmail;
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiration;
    
    @Builder.Default
    private Boolean active = true;
    
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = LocalDateTime.now(); }
}