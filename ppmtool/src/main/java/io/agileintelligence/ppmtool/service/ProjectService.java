package io.agileintelligence.ppmtool.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.exception.SqlBadRequestException;
import io.agileintelligence.ppmtool.repository.BacklogRepository;
import io.agileintelligence.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	BacklogRepository backlogRepository;

	
	public Project saveOrUpdateProject(Project project, User user) {
		
		Project out = null;
		
			if (project.getId() == null) {
				
				Project anyprojet = this.projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
				
				if (anyprojet != null) {
					SqlBadRequestException exception = new SqlBadRequestException("projectIdentifier", "value should be unique",null,null);
					throw exception;
				}
				
				Backlog backlog = new Backlog();
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier());
				backlog.setPtsequence(0);
				project.setBacklog(backlog);
				project.setUser(user);

				out = this.projectRepository.save(project);

			} else {
				Project project2 = this.projectRepository.findByProjectIdentifierAndUser(project.getProjectIdentifier(), user);
				
				if(project2 == null) {
					throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
				}
				
				project2.setDescription(project.getDescription());
				project2.setProjectName(project.getProjectName());
				project2.setEndDate(project.getEndDate());
				project2.setStartDate(project.getStartDate());
				
				out = this.projectRepository.save(project2);
			}
			
		return out;
	}
	
	
	
	public Project findProject(String identifier, User user) {
		
		Project project = this.projectRepository.findByProjectIdentifierAndUser(identifier, user);
		
		if(project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}
		
		return project;
	}
	
	public Iterable<Project> findAll(User user) {
		
		return this.projectRepository.findByUser(user);
	}
	
	public void deleteProject(String identifier, User user) {
		
		Project project = this.projectRepository.findByProjectIdentifierAndUser(identifier, user);
		
		if(project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}
		
		this.projectRepository.delete(project);
	}
}
