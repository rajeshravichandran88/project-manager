package com.fse.projectmanager;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.fse.projectmanager.accessor.TaskAccessor;
import com.fse.projectmanager.accessor.TaskRepository;
import com.fse.projectmanager.entity.Task;
import com.fse.projectmanager.service.TaskRestService;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

	@InjectMocks
	private TaskRestService taskRestService;
	
	@Spy
	private TaskAccessor taskAccessor;
	
	@Spy
	private TaskRepository taskRepository;
	
	@Test
	public void testGetAllTasks() {
		Task task = new Task();
		task.setTaskId(1);
		List<Task> list = Arrays.asList(task);
		
		Mockito.when(taskAccessor.getTasks()).thenReturn(list);
		list = taskRestService.findTasks();
		
		assertEquals(true, list.size() > 0);
	}
	
	@Test
	public void testAddTask() {
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("test");
		
		Date today = new Date();
		task.setStartDate(today);
		
		today.setYear(2020);
		task.setEndDate(today);
		task.setPriority(30);
		
		Mockito.when(taskAccessor.saveTask(task)).thenReturn(true);
		ResponseEntity<String> response = taskRestService.addTask(task);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testGetTaskById() {
		Task task = new Task();
		task.setTaskId(1);
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		task.setParentId(1);
		task.setPriority(10);
		task.setStatus("Open");
		task.setTask("Task");
		task.setProjectId(1);
		
		Mockito.when(taskAccessor.getTask(1)).thenReturn(task);
		task = taskRestService.findTask(1);
		String taskName = task.getTask();
		Date startDate = task.getStartDate();
		Date endDate = task.getEndDate();
		int projectId = task.getProjectId();
		int priority = task.getPriority();
		String status = task.getStatus();
		int parentId = task.getParentId();
		int taskId = task.getTaskId();
		
		assertEquals(true, null != task);
	}
	
	@Test
	public void testDeleteTask1() {
		Mockito.when(taskAccessor.deleteTask(1)).thenReturn(true);
		ResponseEntity<String> response = taskRestService.deleteTask(1);
		
		assertEquals(true, response.getBody().contains("true"));
	}
}
