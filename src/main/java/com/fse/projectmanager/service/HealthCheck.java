package com.fse.projectmanager.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component("healthCheck")
@CrossOrigin
@Controller
public class HealthCheck {

	@RequestMapping("/healthcheck")
	@GetMapping
	public @ResponseBody String healthCheck() {
		return "Application is UP!";
	}
	
}
