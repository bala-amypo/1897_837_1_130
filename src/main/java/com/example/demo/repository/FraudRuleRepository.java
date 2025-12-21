package com.example.demo.repository;

import com.example.demo.model.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface FraudRuleRepository
        extends JpaRepository<FraudRule, Long> {

    Optional<FraudRule> findByRuleCode(String ruleCode);

    boolean existsByRuleCode(String ruleCode);

    List<FraudRule> findByActiveTrue();
}
