package com.example.demo.service;

import com.example.demo.model.FraudRule;

import java.util.List;

public interface FraudRuleService {

    FraudRule create(FraudRule rule);

    FraudRule update(Long id, FraudRule rule);

    FraudRule getById(Long id);

    FraudRule getByCode(String ruleCode);

    List<FraudRule> getAll();

    List<FraudRule> getActive();
}
