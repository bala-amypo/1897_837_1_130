package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService; // Import the interface
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class FraudRuleServiceImpl implements FraudRuleService { // <--- CRITICAL FIX: implements Interface
    private final FraudRuleRepository repo;

    public FraudRuleServiceImpl(FraudRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public FraudRule createRule(FraudRule rule) {
        if (repo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new IllegalArgumentException("Rule already exists");
        }
        return repo.save(rule);
    }

    @Override
    public FraudRule updateRule(Long id, FraudRule updatedRule) {
        FraudRule rule = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Rule not found"));
        rule.setDescription(updatedRule.getDescription());
        // Update other fields if necessary based on your project needs
        return repo.save(rule);
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public Optional<FraudRule> getRuleByCode(String code) {
        return repo.findByRuleCode(code);
    }
    
    @Override
    public List<FraudRule> getAllRules() {
        return repo.findAll();
    }
}