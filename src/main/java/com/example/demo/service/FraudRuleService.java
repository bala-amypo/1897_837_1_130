package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.FraudRule;

public interface FraudRuleService {

    FraudRule createRule(FraudRule rule);

    List<FraudRule> getActiveRules();

    Optional<FraudRule> getRuleByCode(String ruleCode);
}