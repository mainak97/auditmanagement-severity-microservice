package com.auditManagement.severity.domain;

import javax.validation.Valid;
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
public class AuditDetailsDto {
	@NotEmpty
	private String projectName;
	@NotEmpty
	private String projectManagerName;
	@NotEmpty
	private String applicationOwnerName;
	@Valid
	@NotNull
	private AuditDetailDto auditDetail;
}
