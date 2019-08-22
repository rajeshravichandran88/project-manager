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

import com.fse.projectmanager.accessor.ProjectAccessor;
import com.fse.projectmanager.accessor.ProjectRepository;
import com.fse.projectmanager.entity.Project;
import com.fse.projectmanager.service.ProjectRestService;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

	@InjectMocks
	private ProjectRestService projectRestService;
	
	@Spy
	private ProjectAccessor projectAccessor;
	
	@Spy
	private ProjectRepository projectRepository;
	
	@Test
	public void testGetAllProjects() {
		Project project = new Project();
		project.setProjectId(1);
		List<Project> list = Arrays.asList(project);
		
		//Mockito.when(projectRepository.findAll()).thenReturn(list);
		Mockito.when(projectAccessor.getProject()).thenReturn(list);
		list = projectRestService.findProjects();
		
		assertEquals(true, list.size() > 0);
	}
	
	@Test
	public void testAddProjects() {
		Project project = new Project();
		project.setProjectId(1);
		project.setProject("test");
		
		Date today = new Date();
		project.setStartDate(today);
		
		today.setYear(2020);
		project.setEndDate(today);
		project.setPriority(30);
		
		//Mockito.when(projectRepository.save(project)).thenReturn(project);
		Mockito.when(projectAccessor.saveProject(project)).thenReturn(true);
		ResponseEntity<String> response = projectRestService.addProject(project);
		
		assertEquals(true, response.getBody().contains("true"));
	}
	
	@Test
	public void testGetProjectById() {
		Project project = new Project();
		project.setProjectId(1);
		project.setProject("test");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setPriority(30);
		
		//Mockito.when(projectRepository.findById(1).get()).thenReturn(project);
		Mockito.when(projectAccessor.getProject(1)).thenReturn(project);
		project = projectRestService.findProject(1);
		int projectId = project.getProjectId();
		String projectDesc = project.getProject();
		Date startDate = project.getStartDate();
		Date endDate = project.getEndDate();
		int priority = project.getPriority();
		project.getFormattedEndDate();
		project.getFormattedStartDate();
		project.getTasks();
		project.getUser();
		
		
		assertEquals(true, null != project);
	}
	
	@Test
	public void testDeleteProject() {
		Mockito.when(projectAccessor.deleteProject(1)).thenReturn(true);
		ResponseEntity<String> response = projectRestService.deleteProject(1);
		
		assertEquals(true, response.getBody().contains("true"));
	}
}
