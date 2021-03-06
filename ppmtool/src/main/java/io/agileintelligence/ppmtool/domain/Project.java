package io.agileintelligence.ppmtool.domain;

//test master entity merge
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
//
@Entity
public class Project {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message="Project name is required")
	private String projectName;
	
	@NotBlank(message="Project identifier required")
	@Size(min=4, max=5, message="min 4 and max 5 character")
	@Column(updatable=false, unique=true)
	private String projectIdentifier;
	
	@NotBlank(message = "descriton required")
	private String description;

	@NotNull(message="Start date is required")
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date startDate;

	@NotNull(message="End date is required")
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date endDate;

	@JsonFormat(pattern="yyyy-mm-dd")
	@Column(updatable=false)
	private Date created_At;

	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updated_At;
	
	
	@OneToOne(mappedBy="project", cascade=javax.persistence.CascadeType.ALL)
	@JsonIgnore
	private Backlog backlog;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String projectLeader;
	
	
	public Project() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}




	public Backlog getBacklog() {
		return backlog;
	}




	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}
	
	
}
