package io.agileintelligence.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsername(username);
	}
}
