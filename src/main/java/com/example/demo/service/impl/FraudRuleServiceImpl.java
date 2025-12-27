package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository ruleRepository;

    public FraudRuleServiceImpl(FraudRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public FraudRule createRule(FraudRule rule) {
        // Test 53 expects IllegalArgumentException
        if (ruleRepository.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new IllegalArgumentException("Rule already exists"); // [cite: 110]
        }
        return ruleRepository.save(rule);
    }

    @Override
    public FraudRule updateRule(Long id, FraudRule updatedRule) {
        FraudRule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
        rule.setRuleCode(updatedRule.getRuleCode());
        rule.setDescription(updatedRule.getDescription());
        rule.setRuleType(updatedRule.getRuleType());
        rule.setActive(updatedRule.getActive());
        return ruleRepository.save(rule);
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }

    @Override
    public Optional<FraudRule> getRuleByCode(String ruleCode) {
        return ruleRepository.findByRuleCode(ruleCode);
    }

    @Override
    public List<FraudRule> getAllRules() {
        return ruleRepository.findAll();
    }
}