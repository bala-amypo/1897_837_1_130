package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository repository;

    public FraudRuleServiceImpl(FraudRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public FraudRule create(FraudRule rule) {
        if (repository.existsByRuleCode(rule.getRuleCode())) {
            throw new BadRequestException("Rule already exists");
        }
        return repository.save(rule);
    }

    @Override
    public FraudRule update(Long id, FraudRule updated) {
        FraudRule rule = getById(id);
        rule.setRuleType(updated.getRuleType());
        rule.setDescription(updated.getDescription());
        rule.setActive(updated.getActive());
        return repository.save(rule);
    }

    @Override
    public FraudRule getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found")
                );
    }

    @Override
    public FraudRule getByCode(String ruleCode) {
        return repository.findByRuleCode(ruleCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Match not found")
                );
    }

    @Override
    public List<FraudRule> getAll() {
        return repository.findAll();
    }

    @Override
    public List<FraudRule> getActive() {
        return repository.findByActiveTrue();
    }
}
