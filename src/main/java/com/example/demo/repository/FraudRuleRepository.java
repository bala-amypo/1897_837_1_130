package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FraudRule;

@Repository
public interface FraudRuleRepository
        extends JpaRepository<FraudRule, Long> {

    Optional<FraudRule> findByRuleCode(String ruleCode);

    List<FraudRule> findByActiveTrue();
}