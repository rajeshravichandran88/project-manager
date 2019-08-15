package com.fse.projectmanagementui.projectmanager;

import static org.junit.Assert.*;

import org.junit.Test;
import com.fse.projectmanager.service.HealthCheck;

public class HealthCheckTest {

	private HealthCheck healthCheck = new HealthCheck();
	
	@Test
	public void testHealthCheck() {
		assertEquals("Application is UP!", healthCheck.healthCheck());
	}
	
}
