package com.auditManagement.severity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.auditManagement.severity.domain.AuditDetailDto;
import com.auditManagement.severity.domain.AuditDetails;
import com.auditManagement.severity.domain.AuditDetailsDto;
import com.auditManagement.severity.repo.AuditDetailsRepo;
import com.auditManagement.severity.service.AuditDetailsServiceImpl;

@WebMvcTest(AuditDetailsServiceImpl.class)
class AuditManagementChecklistServiceTests {
	@Mock
	private AuditDetailsRepo auditDetailsRepo;
	
	@MockBean
	private AuditDetailsServiceImpl auditDetailsServiceImpl;
	
	@InjectMocks
	private AuditDetailsServiceImpl auditDetailsService;
	
	
	@Test
    void saveTest() throws Exception {
		AuditDetailsDto auditDetails = new AuditDetailsDto("", "", "", new AuditDetailDto("",new Date(),1,4));
		AuditDetails finalAuditDetails = new AuditDetails(auditDetails);
    	Mockito.when(auditDetailsRepo.save(finalAuditDetails)).thenReturn(finalAuditDetails);
    	assertThat(auditDetailsService.saveAuditDetails(auditDetails)).isNull();
    	
    }
	
	@Test
    void saveREDTest() throws Exception {
		AuditDetailsDto auditDetails = new AuditDetailsDto("", "", "", new AuditDetailDto("",new Date(),3,2));
		AuditDetails finalAuditDetails = new AuditDetails(auditDetails);
    	Mockito.when(auditDetailsRepo.save(finalAuditDetails)).thenReturn(finalAuditDetails);
    	assertThat(auditDetailsService.saveAuditDetails(auditDetails)).isNull();
    	
    }
}
