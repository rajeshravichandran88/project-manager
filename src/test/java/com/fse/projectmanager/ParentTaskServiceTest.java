package com.fse.projectmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fse.projectmanager.accessor.ParentTaskAccessor;
import com.fse.projectmanager.accessor.ParentTaskRepository;
import com.fse.projectmanager.entity.ParentTask;
import com.fse.projectmanager.service.ParentTaskRestService;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskServiceTest {
	
	@InjectMocks
	private ParentTaskRestService parentTaskService;

	@InjectMocks
	private ParentTaskAccessor parentTaskAccessor;
	
	@Mock
	private ParentTaskRepository parentTaskRepository;
	
	@Spy
	private ParentTaskAccessor parentTaskAccessor1;
	
	@Spy
	private ParentTaskRepository parentTaskRepository1;
	
	@Autowired
    private RestTemplate restTemplate = new RestTemplate();
	
	private String url = "http://localhost:9000/projectmanager/parenttask";
	
	@Test
	public void testGetAllParentTasks() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		List<ParentTask> list = Arrays.asList(parentTask);
		
		Mockito.when(parentTaskAccessor.getParentTask()).thenReturn(list);
		Mockito.when(parentTaskRepository.findAll()).thenReturn(list);
		list = parentTaskAccessor.getParentTask();
		
		assertEquals(true, list.size() > 0);
	}
	
	@Test
	public void testAddParentTask() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("test");
		
		Mockito.when(parentTaskAccessor.saveParentTask(parentTask)).thenReturn(true);
		//Mockito.when(parentTaskRepository.save(parentTask)).thenThrow(new Exception());
		boolean status = parentTaskAccessor.saveParentTask(parentTask);
		
		assertEquals(false, status);
	}
	
	@Test
	public void testGetParentTaskById() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(0);
		
		Mockito.when(parentTaskAccessor.getParentTask(0)).thenReturn(null);
		Mockito.when(parentTaskRepository.findById(0)).thenReturn(null);
		parentTask = parentTaskAccessor.getParentTask(0);
		
		assertEquals(true, null == parentTask);
	}
	
	@Test
	public void testAddValidParentTask() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("Parent Task");
		
		ResponseEntity<String> response = restTemplate.postForEntity(url + "/add", parentTask, String.class);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testGetParentTaskById1() {
		
		ResponseEntity<ParentTask> response = restTemplate.getForEntity(url + "/1", ParentTask.class);
		
		assertNotNull(response);
	}
	
	@Test
	public void testGetParentTasks() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(url + "/", String.class);
		
		assertEquals(true, response.getBody().contains("\"parentId\":1"));
	}
	
	@Test
	public void testDeleteValidParentTask() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("Parent Task");
		
		ResponseEntity<String> response = restTemplate.postForEntity(url + "/delete/1", parentTask, String.class);
		
		assertEquals(true, response.getBody().contains("false"));
	}
	
	@Test
	public void testGetAllParentTasks2() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		List<ParentTask> list = Arrays.asList(parentTask);
		
		Mockito.when(parentTaskAccessor1.getParentTask()).thenReturn(list);
		list = parentTaskService.findParentTask();
		
		assertEquals(true, list.size() > 0);
	}
	
	@Test
	public void testAddParentTask1() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("test");
		
		Mockito.when(parentTaskAccessor1.saveParentTask(parentTask)).thenReturn(true);
		ResponseEntity<String> response = parentTaskService.addParentTask(parentTask);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testDeleteParentTask1() {
		//Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(parentTaskAccessor1.deleteParentTask(1)).thenReturn(true);
		ResponseEntity<String> response = parentTaskService.deleteParentTask(1);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testGetParentTaskById2() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("test");
		
		Mockito.when(parentTaskAccessor1.getParentTask(1)).thenReturn(parentTask);
		parentTask = parentTaskService.findParentTask(1);
		
		assertEquals(true, null != parentTask);
	}
	
}
