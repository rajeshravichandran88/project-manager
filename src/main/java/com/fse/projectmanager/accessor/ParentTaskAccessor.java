package com.fse.projectmanager.accessor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fse.projectmanager.entity.ParentTask;

@Component("parentTaskAccessor")
public class ParentTaskAccessor {

	private static final Logger LOGGER = LogManager.getLogger(ParentTaskAccessor.class);
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	public List<ParentTask> getParentTask() {
		List<ParentTask> parentTaskList = null;
		try {
			parentTaskList = parentTaskRepository.findAll();
		} catch(NoSuchElementException e) {
			return new ArrayList<ParentTask>();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return parentTaskList;
	}
	
	public ParentTask getParentTask(Integer parentTaskId) {
		ParentTask parentTask = null;
		try {
			parentTask = parentTaskRepository.findById(parentTaskId).get();
		} catch(NoSuchElementException e) {
			return null;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return parentTask;
	}
	
	public boolean saveParentTask(ParentTask parentTask) {
		boolean status = false;
		try {
			if(parentTask != null) {
				parentTaskRepository.save(parentTask);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
	public boolean deleteParentTask(Integer parentTaskId) {
		boolean status = false;
		try {
			if(parentTaskId != null && parentTaskId > 0) {
				parentTaskRepository.deleteById(parentTaskId);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
}
