package com.auditManagement.severity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auditManagement.severity.domain.AuditDetails;
import com.auditManagement.severity.domain.AuditDetailsDto;
import com.auditManagement.severity.service.AuditDetailsService;

import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class SeverityController {
	private final AuditDetailsService auditDetailsService;
	
	@PostMapping("/projectexecutionstatus")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	allowEmptyValue = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	public ResponseEntity<AuditDetails> postAuditDetails(@Valid @RequestBody AuditDetailsDto auditDetails, HttpServletRequest request) {
		AuditDetails auditDetailsSaved = null;
		log.info("auditDetails {}",auditDetails.toString());
		auditDetailsSaved = auditDetailsService.saveAuditDetails(auditDetails);
		return ResponseEntity.status(201).body(auditDetailsSaved);
	}
}


