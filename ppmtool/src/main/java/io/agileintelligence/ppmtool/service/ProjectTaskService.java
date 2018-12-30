package io.agileintelligence.ppmtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.exception.EntityNotfoundException;
import io.agileintelligence.ppmtool.exception.SqlBadRequestException;
import io.agileintelligence.ppmtool.repository.BacklogRepository;
import io.agileintelligence.ppmtool.repository.ProjectRepository;
import io.agileintelligence.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	
	@Autowired
	ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	BacklogRepository backlogRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Transactional(readOnly=false)
	public ProjectTask saveProjectTask(String projectIdentifier, ProjectTask projectTask, User user) {

		
		
		Project project = this.projectRepository.findByProjectIdentifierAndUser(projectIdentifier, user);
		
		if (project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}
		
		ProjectTask projectTask3 = null;

		ProjectTask projectTask2 = this.projectTaskRepository.findByProjectSequnce(projectTask.getProjectSequnce());

		//new task
		if (projectTask2 == null) {

			Backlog backlog = this.backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			if (backlog == null) {
				throw new EntityNotfoundException("Backlog");
			}
			
			backlog.setPtsequence(backlog.getPtsequence()+1);
			backlog =  this.backlogRepository.save(backlog);

			projectTask.setProjectSequnce(backlog.getProjectIdentifier()+backlog.getPtsequence());
			projectTask.setProjectIdentifier(backlog.getProjectIdentifier());
			projectTask.setBacklog(backlog);

			projectTask3 = this.projectTaskRepository.save(projectTask);
		} else {

			
			projectTask2.setAcceptanceCriteria(projectTask.getAcceptanceCriteria());
			projectTask2.setDueDate(projectTask.getDueDate());
			projectTask2.setSummary(projectTask.getSummary());
			projectTask2.setStatus(projectTask.getStatus());
			projectTask2.setPriority(projectTask.getPriority());
			projectTask3 = this.projectTaskRepository.save(projectTask2);
		}
		
		return projectTask3;
	}
	
	public List<ProjectTask> findTasksByIdentifier(String identifier, User user) {

		Project project = this.projectRepository.findByProjectIdentifierAndUser(identifier, user);
		
		if (project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}

		return this.projectTaskRepository.findByProjectIdentifier(identifier);
	}

	public ProjectTask findTaskByProjectSequence(String identifier, String seq, User user) {
		
		Project project = this.projectRepository.findByProjectIdentifierAndUser(identifier, user);
		
		if (project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}
		
		ProjectTask projectTask = this.projectTaskRepository.findByProjectSequnce(seq);

		if (projectTask == null) {
			throw new EntityNotfoundException("Project task");
		}
		
		return projectTask;

	}
	
	public void deleteTask(String identifier, String seq, User user) {

		Project project = this.projectRepository.findByProjectIdentifierAndUser(identifier, user);
		
		if (project == null) {
			throw new SqlBadRequestException("projectIdentifier", "Project doesn't exist", null, null);
		}

		ProjectTask projectTask = this.projectTaskRepository.findByProjectSequnce(seq);

		if (projectTask == null) {
			throw new EntityNotfoundException("Project task");
		}

		this.projectTaskRepository.delete(projectTask);
	}
}
