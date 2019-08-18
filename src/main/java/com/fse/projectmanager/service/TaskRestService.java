package com.fse.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fse.projectmanager.accessor.TaskAccessor;
import com.fse.projectmanager.entity.Task;

@Controller
@CrossOrigin
@RequestMapping("/task")
public class TaskRestService {
	
	@Autowired
	private TaskAccessor taskAccessor;

	@RequestMapping(value = "/{taskId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Task findTask(@PathVariable("taskId") Integer taskId) {
		return taskAccessor.getTask(taskId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Task> findTasks() {
		return taskAccessor.getTasks();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> addTask(@RequestBody Task task) {
		boolean status = taskAccessor.saveTask(task);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{taskId}", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> deleteTask(@PathVariable("taskId") Integer taskId) {
		boolean status = taskAccessor.deleteTask(taskId);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
}
