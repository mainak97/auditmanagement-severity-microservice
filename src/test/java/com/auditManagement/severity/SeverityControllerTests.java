package com.auditManagement.severity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.auditManagement.severity.models.ErrorResponse;
import com.auditManagement.severity.service.AuditDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auditManagement.severity.controller.SeverityController;
import com.auditManagement.severity.domain.AuditDetailDto;
import com.auditManagement.severity.domain.AuditDetailsDto;

@WebMvcTest(SeverityController.class)
class SeverityControllerTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@MockBean
	private AuditDetailsService auditDetailsService;
	
    @Test
    void postAuditDetailsTest() throws Exception {
    	ErrorResponse error = new ErrorResponse();
	    ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error,HttpStatus.OK);
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenReturn(response);
    	ObjectMapper objectMapper = new ObjectMapper();
    	AuditDetailsDto auditDetails = new AuditDetailsDto("Name","Name","Name", new AuditDetailDto("Type",new Date(),1,4));
    	String json = objectMapper.writeValueAsString(auditDetails);
        mvc.perform(post("/api/projectexecutionstatus").header("Authorization", "Bearer simpletoken").contentType("application/json")
        		.content(json))
          .andExpect(status().isCreated());
    }
    
    @Test
    void postAuditDetailsInvalidTest() throws Exception {
    	ErrorResponse error = new ErrorResponse();
	    ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error,HttpStatus.OK);
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenReturn(response);
    	ObjectMapper objectMapper = new ObjectMapper();
    	AuditDetailsDto auditDetails = new AuditDetailsDto("Name","Name","Name", null);
    	String json = objectMapper.writeValueAsString(auditDetails);
        mvc.perform(post("/api/projectexecutionstatus").header("Authorization", "Bearer simpletoken").contentType("application/json")
        		.content(json))
          .andExpect(status().isBadRequest());
    }
    
    @Test
    void authErrorTest() throws Exception {
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenThrow(new RuntimeException("500 : '{}'"));
    	ObjectMapper objectMapper = new ObjectMapper();
    	AuditDetailsDto auditDetails = new AuditDetailsDto("Name","Name","Name", new AuditDetailDto("Type",new Date(),1,4));
    	String json = objectMapper.writeValueAsString(auditDetails);
        mvc.perform(post("/api/projectexecutionstatus").header("Authorization", "Bearer simpletoken").contentType("application/json")
        		.content(json))
          .andExpect(status().is5xxServerError());
    }
    
    @Test
    void authServerErrorTest() throws Exception {
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenThrow(new RuntimeException(""));
    	ObjectMapper objectMapper = new ObjectMapper();
    	AuditDetailsDto auditDetails = new AuditDetailsDto("Name","Name","Name", new AuditDetailDto("Type",new Date(),1,4));
    	String json = objectMapper.writeValueAsString(auditDetails);
        mvc.perform(post("/api/projectexecutionstatus").header("Authorization", "Bearer simpletoken").contentType("application/json")
        		.content(json))
          .andExpect(status().is5xxServerError());
    }
}
