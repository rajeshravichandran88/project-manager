package com.fse.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fse.projectmanager.accessor.ParentTaskAccessor;
import com.fse.projectmanager.entity.ParentTask;

@Controller
@RequestMapping("/parenttask")
public class ParentTaskRestService {
	
	@Autowired
	private ParentTaskAccessor parentTaskAccessor;

	@RequestMapping(value = "/{parentTaskId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ParentTask findParentTask(@PathVariable("parentTaskId") Integer parentTaskId) {
		return parentTaskAccessor.getParentTask(parentTaskId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ParentTask> findParentTask() {
		return parentTaskAccessor.getParentTask();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> addParentTask(@RequestBody ParentTask parentTask) {
		boolean status = parentTaskAccessor.saveParentTask(parentTask);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{parentTaskId}", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> deleteParentTask(@PathVariable("parentTaskId") Integer parentTaskId) {
		boolean status = parentTaskAccessor.deleteParentTask(parentTaskId);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
}
