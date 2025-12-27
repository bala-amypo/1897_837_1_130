package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private String ruleType;
    private String description;
    private Boolean active = true;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FraudRule r = new FraudRule();

        public Builder id(Long id) { r.id = id; return this; }
        public Builder ruleCode(String c) { r.ruleCode = c; return this; }
        public Builder ruleType(String t) { r.ruleType = t; return this; }
        public Builder description(String d) { r.description = d; return this; }

        public FraudRule build() { return r; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public String getRuleType() { return ruleType; }
    public Boolean getActive() { return active; }
}