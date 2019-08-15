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

import com.fse.projectmanager.accessor.ProjectAccessor;
import com.fse.projectmanager.entity.Project;

@Controller
@RequestMapping("/project")
public class ProjectRestService {
	
	@Autowired
	private ProjectAccessor projectAccessor;

	@RequestMapping(value = "/{projectId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Project findProject(@PathVariable("projectId") Integer projectId) {
		return projectAccessor.getProject(projectId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Project> findProjects() {
		return projectAccessor.getProject();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> addProject(@RequestBody Project project) {
		boolean status = projectAccessor.saveProject(project);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{projectId}", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> deleteProject(@PathVariable("projectId") Integer projectId) {
		boolean status = projectAccessor.deleteProject(projectId);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
}
