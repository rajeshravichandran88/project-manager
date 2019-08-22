package com.fse.projectmanager;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fse.projectmanager.accessor.UserAccessor;
import com.fse.projectmanager.accessor.UserRepository;
import com.fse.projectmanager.entity.Task;
import com.fse.projectmanager.entity.User;
import com.fse.projectmanager.service.UserRestService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserRestService userService;
	
	@Spy
	private UserAccessor userAccessor;
	
	@Spy
	private UserRepository userRepository;
	
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
		
		ResponseEntity<User> response = restTemplate.getForEntity(url + "/68", User.class);
		
		assertEquals(new Integer(68), response.getBody().getUserId());
	}
	
	@Test
	public void testGetAllUsers() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(url + "/", String.class);
		assertEquals(true, response.getBody().contains("userId"));
	}
	
	/*@Test
	public void testGetUserById1() {
		User user = new User();
		user.setUserId(1);
		
		Mockito.when(userRepository.findById(1).get()).thenReturn(user);
		Mockito.when(userAccessor.getUser(1)).thenReturn(user);
		user = userService.findUser(1);
		
		assertEquals(new Integer(1), user.getUserId());
	}*/
	
	@Test
	public void testGetAllUsers1() {
		User user = new User();
		user.setUserId(1);
		List<User> list = Arrays.asList(user);
		
		//Mockito.when(userRepository.findAll()).thenReturn(list);
		Mockito.when(userAccessor.getUsers()).thenReturn(list);
		list = userService.findUsers();
		
		assertEquals(true, list.size() > 0);
	}
	
	@Test
	public void testAddUser1() {
		User user = new User();
		user.setUserId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmployeeId("999");
		user.setProjectId(1);
		user.setTaskId(1);
		
		//Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userAccessor.saveUser(user)).thenReturn(true);
		ResponseEntity<String> response = userService.addUser(user);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testDeleteUser1() {
		//Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userAccessor.deleteUser(1)).thenReturn(true);
		ResponseEntity<String> response = userService.deleteUser(1);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testGetUserById1() {
		User user = new User();
		user.setUserId(1);
		
		Mockito.when(userAccessor.getUser(1)).thenReturn(user);
		user = userService.findUser(1);
		
		assertEquals(true, null != user);
	}

}
