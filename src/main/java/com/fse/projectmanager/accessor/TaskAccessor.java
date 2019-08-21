package com.fse.projectmanager.accessor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fse.projectmanager.entity.ParentTask;
import com.fse.projectmanager.entity.Task;

@Component("taskAccessor")
public class TaskAccessor {

	private static final Logger LOGGER = LogManager.getLogger(TaskAccessor.class);
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	public List<Task> getTasks() {
		List<Task> taskList = null;
		try {
			taskList = taskRepository.findAll();
			for(Task task: taskList) {
				ParentTask parentTask = null;
				if(null != task.getParentId()) {
					parentTask = parentTaskRepository.findById(task.getParentId()).get();
				}
				task.setParentTask(parentTask);
			}
		} catch(NoSuchElementException e) {
			return new ArrayList<Task>();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return taskList;
	}
	
	public Task getTask(Integer taskId) {
		Task task = null;
		try {
			task = taskRepository.findById(taskId).get();
		} catch(NoSuchElementException e) {
			return null;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return task;
	}
	
	public boolean saveTask(Task task) {
		boolean status = false;
		try {
			if(task != null) {
				taskRepository.save(task);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
	public boolean deleteTask(Integer taskId) {
		boolean status = false;
		try {
			if(taskId != null && taskId > 0) {
				taskRepository.deleteById(taskId);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
}
