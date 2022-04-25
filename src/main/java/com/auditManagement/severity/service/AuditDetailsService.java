package com.auditManagement.severity.service;

import com.auditManagement.severity.domain.AuditDetails;
import com.auditManagement.severity.domain.AuditDetailsDto;

public interface AuditDetailsService {
	AuditDetails saveAuditDetails(AuditDetailsDto auditDetails);
}
