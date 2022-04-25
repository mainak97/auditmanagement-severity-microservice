package com.auditManagement.severity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auditManagement.severity.domain.AuditDetails;

public interface AuditDetailsRepo extends JpaRepository<AuditDetails,Long> {

}
