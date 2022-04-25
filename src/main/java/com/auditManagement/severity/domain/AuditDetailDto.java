package com.auditManagement.severity.domain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class AuditDetailDto {
	@NotEmpty
	private String auditType;
	@NotNull
	private Date auditDate;
	@NotNull
	private Integer auditQuestionsYes;
	@NotNull
	private Integer auditQuestionsNo;
}
