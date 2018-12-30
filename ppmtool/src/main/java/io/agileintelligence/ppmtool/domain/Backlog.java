package io.agileintelligence.ppmtool.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Backlog {
	
	public Backlog() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer ptsequence = 0;
	private String projectIdentifier;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="project_id")
	private Project project;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="backlog")
	private List<ProjectTask> projectTasks;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPtsequence() {
		return ptsequence;
	}
	public void setPtsequence(Integer ptsequence) {
		this.ptsequence = ptsequence;
	}
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<ProjectTask> getProjectTasks() {
		return projectTasks;
	}
	public void setProjectTasks(List<ProjectTask> projectTasks) {
		this.projectTasks = projectTasks;
	}
	
	
}
