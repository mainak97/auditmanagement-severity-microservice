package com.auditManagement.severity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.auditManagement.severity.domain.AuditDetailDto;
import com.auditManagement.severity.domain.AuditDetails;
import com.auditManagement.severity.domain.AuditDetailsDto;
import com.auditManagement.severity.models.ErrorResponse;

@SpringBootTest
class SeverityModelsAndEntityTests {
	@Test
	void ErrorResponseTest() {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(1000);
		response.setErrorMsg("error");
		assertThat(response.getErrorCode()).isEqualTo(1000);
		assertThat(response.getErrorMsg()).isEqualTo("error");
		assertThat(response.toString()).hasToString("ErrorResponse(errorCode=1000, errorMsg=error)");
		ErrorResponse response1 = new ErrorResponse(1000,"error");
		assertThat(response1).isNotNull();
	}
	@Test
	void AuditDetailsDtoTest() {
		AuditDetailsDto auditDetailsDto = new AuditDetailsDto("Name","Name","Name",new AuditDetailDto());
		assertThat(auditDetailsDto).isNotNull();
	}
	@Test
	void AuditDetailsTest() {
		Date date = new Date();
		AuditDetailsDto auditDetailsDto = new AuditDetailsDto("Name","Name","Name",
				new AuditDetailDto("Type",date,1,4));
		AuditDetails auditDetails = new AuditDetails(auditDetailsDto);
		auditDetails.setApplicationOwnerName("Name");
		auditDetails.setProjectExecutionStatus("RED");
		auditDetails.setProjectManagerName("Name");
		auditDetails.setProjectName("Name");
		auditDetails.setAuditDate(date);
		auditDetails.setAuditQuestionsNo(4);
		auditDetails.setAuditQuestionsYes(1);
		auditDetails.setAuditType("Type");
		auditDetails.setRemedialAction("Action");
		auditDetails.setId(1L);
		assertThat(auditDetails.getApplicationOwnerName()).isEqualTo("Name");
		assertThat(auditDetails.getProjectManagerName()).isEqualTo("Name");
		assertThat(auditDetails.getProjectName()).isEqualTo("Name");
		assertThat(auditDetails.getAuditDate()).isEqualTo(date);
		assertThat(auditDetails.getAuditType()).isEqualTo("Type");
		assertThat(auditDetails.getAuditQuestionsNo()).isEqualTo(4);
		assertThat(auditDetails.getAuditQuestionsYes()).isEqualTo(1);
		assertThat(auditDetails.getRemedialAction()).isEqualTo("Action");
		assertThat(auditDetails.getId()).isEqualTo(1L);
		assertThat(auditDetails.getProjectExecutionStatus()).isEqualTo("RED");
		
		AuditDetails auditDetails1 = new AuditDetails(1L,"Name", "Name", "Name", "Name", date, 1, 4, "Name", "Name");
		assertThat(auditDetails1).isNotNull();
	}
}
