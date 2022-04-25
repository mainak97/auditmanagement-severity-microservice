package com.auditManagement.severity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auditManagement.severity.domain.AuditDetails;
import com.auditManagement.severity.domain.AuditDetailsDto;
import com.auditManagement.severity.repo.AuditDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AuditDetailsServiceImpl implements AuditDetailsService {
	private final AuditDetailsRepo auditDetailsRepo;
	
	@Override
	public AuditDetails saveAuditDetails(AuditDetailsDto auditDetails) {
		String projectExecutionStatus = "RED";
		String remedialAction = "Action to be taken in 2 weeks";
		if(auditDetails.getAuditDetail().getAuditQuestionsNo() <= 3) {
			projectExecutionStatus = "GREEN";
			remedialAction = "No action needed";
		}
		AuditDetails finalAuditDetails = new AuditDetails(auditDetails);
		log.info(auditDetails.getAuditDetail().getAuditDate().toString());
		finalAuditDetails.setProjectExecutionStatus(projectExecutionStatus);
		finalAuditDetails.setRemedialAction(remedialAction);
		return auditDetailsRepo.save(finalAuditDetails);
		
	}

}
