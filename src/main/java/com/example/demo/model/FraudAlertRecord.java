package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;
    private String serialNumber;
    private Boolean resolved = false;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FraudAlertRecord f = new FraudAlertRecord();

        public Builder id(Long id) { f.id = id; return this; }
        public Builder claimId(Long c) { f.claimId = c; return this; }
        public Builder serialNumber(String s) { f.serialNumber = s; return this; }
        public Builder resolved(Boolean r) { f.resolved = r; return this; }

        public FraudAlertRecord build() { return f; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClaimId() { return claimId; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}