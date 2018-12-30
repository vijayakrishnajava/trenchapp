package io.agileintelligence.ppmtool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findByProjectIdentifier(String identifier);
	
	Project findByProjectIdentifierAndUser(String identifier, User user);
	
	List<Project> findByUser(User user);
	
}
