package com.example.demo.repository;
import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

interface DeviceOwnershipRecordRepository extends JpaRepository<DeviceOwnershipRecord, Long> {
    Optional<DeviceOwnershipRecord> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}

interface WarrantyClaimRecordRepository extends JpaRepository<WarrantyClaimRecord, Long> {
    List<WarrantyClaimRecord> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumberAndClaimReason(String serialNumber, String claimReason);
}

interface StolenDeviceReportRepository extends JpaRepository<StolenDeviceReport, Long> {
    List<StolenDeviceReport> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}

interface FraudRuleRepository extends JpaRepository<FraudRule, Long> {
    Optional<FraudRule> findByRuleCode(String ruleCode);
    List<FraudRule> findByActiveTrue();
}

interface FraudAlertRecordRepository extends JpaRepository<FraudAlertRecord, Long> {
    List<FraudAlertRecord> findByClaimId(Long claimId);
}