package com.auditManagement.severity.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class AuditDetails {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String projectName;
	@Column(nullable=false)
	private String projectManagerName;
	@Column(nullable=false)
	private String applicationOwnerName;
	@Column(nullable=false)
	private String auditType;
	@Column(nullable=false)
	private Date auditDate;
	@Column(nullable=false)
	private Integer auditQuestionsYes;
	@Column(nullable=false)
	private Integer auditQuestionsNo;
	@Column(nullable=false)
	private String projectExecutionStatus;
	@Column(nullable=false)
	private String remedialAction;
	
	public AuditDetails(AuditDetailsDto auditDetailsDto) {
		this.projectName = auditDetailsDto.getProjectName();
		this.projectManagerName = auditDetailsDto.getProjectManagerName();
		this.applicationOwnerName = auditDetailsDto.getApplicationOwnerName();
		this.auditType = auditDetailsDto.getAuditDetail().getAuditType();
		this.auditDate = auditDetailsDto.getAuditDetail().getAuditDate();
		this.auditQuestionsYes = auditDetailsDto.getAuditDetail().getAuditQuestionsYes();
		this.auditQuestionsNo = auditDetailsDto.getAuditDetail().getAuditQuestionsNo();
	}
}
