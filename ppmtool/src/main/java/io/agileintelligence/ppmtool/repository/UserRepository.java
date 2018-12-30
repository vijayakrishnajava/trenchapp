package io.agileintelligence.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
