package com.fse.projectmanager.accessor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fse.projectmanager.entity.Project;

@Component("projectAccessor")
public class ProjectAccessor {

	private static final Logger LOGGER = LogManager.getLogger(ProjectAccessor.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public List<Project> getProject() {
		List<Project> projectList = null;
		try {
			projectList = projectRepository.findAll();
		} catch(NoSuchElementException e) {
			return new ArrayList<Project>();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return projectList;
	}
	
	public Project getProject(Integer projectId) {
		Project project = null;
		try {
			project = projectRepository.findById(projectId).get();
		} catch(NoSuchElementException e) {
			return null;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return project;
	}
	
	public boolean saveProject(Project project) {
		boolean status = false;
		try {
			if(project != null) {
				projectRepository.save(project);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
	public boolean deleteProject(Integer projectId) {
		boolean status = false;
		try {
			if(projectId != null && projectId > 0) {
				projectRepository.deleteById(projectId);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
}
