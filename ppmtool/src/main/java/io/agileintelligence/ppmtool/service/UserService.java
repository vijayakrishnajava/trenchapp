package io.agileintelligence.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.exception.InvalidLoginResponse;
import io.agileintelligence.ppmtool.exception.LoginException;
import io.agileintelligence.ppmtool.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User user) {
		
		User existingUser = this.userRepository.findByUsername(user.getUsername());
		
		if ((existingUser != null) && (existingUser.getId() != null)) {
			InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse(); 
			invalidLoginResponse.setUsername("User already exists");
			
			LoginException loginException = new LoginException();
			loginException.setResponse(invalidLoginResponse);
			
			throw loginException;
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		return this.userRepository.save(user);
	}


	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	public User getUserFromSecurityContext() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User loggedinUser = (User)authentication.getPrincipal();
		return loggedinUser;
	}
	
}
