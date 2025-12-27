package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService ruleService;

    public FraudRuleController(FraudRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<?> createRule(
            @RequestBody FraudRule rule
    ) {
        return ResponseEntity.status(201)
                .body(ruleService.createRule(rule));
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getRuleByCode(
            @PathVariable String code
    ) {
        return ResponseEntity.ok(
                ruleService.getRuleByCode(code)
        );
    }
}