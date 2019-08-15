package com.fse.projectmanagementui.projectmanager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fse.projectmanager.entity.User;
import com.fse.projectmanager.service.UserRestService;

import static org.junit.Assert.*;

import java.util.List;

public class UserServiceTest {
	
	//private UserRestService userService = new UserRestService();
	@Autowired
    private RestTemplate restTemplate = new RestTemplate();
	
	private String url = "http://localhost:9000/projectmanager/user";
	
	@Test
	public void testAddValidUser() {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmployeeId("000");
		user.setProjectId(1);
		user.setTaskId(1);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url + "/add", user, String.class);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testAddInvalidUser() {
		User user = new User();
		user.setFirstName(null);
		user.setLastName(null);
		user.setEmployeeId("000");
		user.setProjectId(1);
		user.setTaskId(1);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url + "/add", user, String.class);
		
		assertEquals(true, response.getBody().contains("false"));
	}
	
	@Test
	public void testEditUser() {
		User user = new User();
		user.setUserId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmployeeId("999");
		user.setProjectId(1);
		user.setTaskId(1);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url + "/add", user, String.class);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testGetUserById() {
		
		ResponseEntity<User> response = restTemplate.getForEntity(url + "/1", User.class);
		
		assertEquals(new Integer(1), response.getBody().getUserId());
	}
	
	@Test
	public void testGetAllUsers() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(url + "/", String.class);
		System.out.println(response);
		assertEquals(true, response.getBody().contains("\"userId\":1"));
	}

}
