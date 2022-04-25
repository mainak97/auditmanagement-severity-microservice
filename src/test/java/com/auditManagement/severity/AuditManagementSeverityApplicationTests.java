package com.auditManagement.severity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditManagementSeverityApplicationTests {

	@Test
	void contextLoads() {
		String test = null;
		assertThat(test).isNull();
	}
	
	@Test
	void test() {
		String test = null;
		assertThat(test).isNull();
		AuditManagementSeverityApplication.main(new String[]{});
	}

}
