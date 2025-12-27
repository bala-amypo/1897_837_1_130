package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService ruleService;

    public FraudRuleController(FraudRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<?> createRule(@RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<?> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRuleById(@PathVariable Long id) {
        // Implementation detail: typically service.getById, explicitly implied by repo structure
        return ResponseEntity.ok(ruleService.getAllRules().stream()
            .filter(r -> r.getId().equals(id)).findFirst().orElseThrow());
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRule(@PathVariable Long id, @RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }
}