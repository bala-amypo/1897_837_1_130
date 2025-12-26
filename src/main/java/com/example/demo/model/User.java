package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "device_ownership_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DeviceOwnershipRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String serialNumber; [cite: 50]
    private String ownerName; [cite: 51]
    private String ownerEmail; [cite: 52]
    private LocalDate purchaseDate; [cite: 53]
    private LocalDate warrantyExpiration; [cite: 54]
    private Boolean active = true; [cite: 55]
    private LocalDateTime createdAt; [cite: 56]

    @PrePersist
    protected void onCreate() { 
        createdAt = LocalDateTime.now(); [cite: 63]
        if (active == null) active = true; [cite: 62]
    }
}

@Entity
@Table(name = "warranty_claim_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WarrantyClaimRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber; [cite: 68]
    private String claimantName; [cite: 69]
    private String claimantEmail; [cite: 70]
    private String claimReason; [cite: 71]
    private LocalDateTime submittedAt; [cite: 72]
    private String status = "PENDING"; [cite: 73]
    private LocalDateTime createdAt; [cite: 74]

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now(); [cite: 80]
        createdAt = LocalDateTime.now(); [cite: 81]
        if (status == null) status = "PENDING"; [cite: 79]
    }
}

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; [cite: 32]
    @Column(unique = true)
    private String email; [cite: 33]
    private String password; [cite: 34]
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>(); [cite: 35]
    private LocalDateTime createdAt; [cite: 36]

    @PrePersist
    protected void onCreate() { 
        createdAt = LocalDateTime.now(); [cite: 43]
        if (roles == null || roles.isEmpty()) roles = Set.of("USER"); [cite: 42]
    }
}

@Entity
@Table(name = "stolen_device_reports")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StolenDeviceReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber; [cite: 89]
    private String reportedBy; [cite: 90]
    private LocalDateTime reportDate; [cite: 91]
    private String details; [cite: 92]

    @PrePersist
    protected void onCreate() { reportDate = LocalDateTime.now(); [cite: 97] }
}

@Entity
@Table(name = "fraud_rules")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FraudRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ruleCode; [cite: 103]
    private String ruleType; [cite: 104]
    private String description; [cite: 105]
    private Boolean active = true; [cite: 106]
    private LocalDateTime createdAt; [cite: 107]

    @PrePersist
    protected void onCreate() { 
        createdAt = LocalDateTime.now(); [cite: 113]
        if (active == null) active = true; [cite: 111]
    }
}

@Entity
@Table(name = "fraud_alert_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FraudAlertRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long claimId; [cite: 118]
    private String serialNumber; [cite: 119]
    private String alertType; [cite: 120]
    private String severity; [cite: 121]
    private String message; [cite: 122]
    private LocalDateTime alertDate; [cite: 123]
    private Boolean resolved = false; [cite: 124]

    @PrePersist
    protected void onCreate() { 
        alertDate = LocalDateTime.now(); [cite: 129]
        if (resolved == null) resolved = false; [cite: 128]
    }
}