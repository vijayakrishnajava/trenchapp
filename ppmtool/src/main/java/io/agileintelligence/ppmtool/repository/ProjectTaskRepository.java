package io.agileintelligence.ppmtool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long>  {
	
	ProjectTask findByProjectSequnce(String seq);
	
	List<ProjectTask> findByProjectIdentifier(String identifier);
	

}
