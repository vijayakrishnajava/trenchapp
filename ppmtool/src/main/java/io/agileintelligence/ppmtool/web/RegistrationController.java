package io.agileintelligence.ppmtool.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.ppmtool.SecurityConstants;
import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.security.JwtTokenGenerator;
import io.agileintelligence.ppmtool.security.LoginRequest;
import io.agileintelligence.ppmtool.security.LoginResponse;
import io.agileintelligence.ppmtool.security.Token;
import io.agileintelligence.ppmtool.service.MapValidationService;
import io.agileintelligence.ppmtool.service.UserService;
import io.agileintelligence.ppmtool.validator.UserValidator;

@RestController
public class RegistrationController {
	
	@Autowired
	MapValidationService mapValidationService;
	
	@Autowired
	UserService userService;

	@Autowired
	UserValidator userValidator;
	
	@Autowired
	JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping
	@RequestMapping("/login")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {

		ResponseEntity<?> errors = this.mapValidationService.MapvalidatedResults(result);
		
		if(errors != null) {
			return errors;
		}
		
		
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenGenerator.generateToken(authentication);
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSucess(true);
		loginResponse.setJwttoken(SecurityConstants.TOKEN_PREFIX+token);
		
		HttpHeaders headers = new HttpHeaders();
		headers.put(SecurityConstants.HEADER_STRING, Arrays.asList(SecurityConstants.TOKEN_PREFIX+token));
		
		
		return new ResponseEntity<>(loginResponse, headers, HttpStatus.OK);
		
	}

	@PostMapping("/validate")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> validateToken(@RequestBody Token token) {
		boolean isValid = this.jwtTokenGenerator.validateToken(token.getToken());
		
		Map<String, Object> out = new HashMap<>();
		out.put("status", isValid);
		out.put("token", token);
		return new ResponseEntity<>(out, HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping("/registration")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> registration(@Valid @RequestBody User user, BindingResult result) {
		
		userValidator.validate(user, result);

		ResponseEntity<?> errors = this.mapValidationService.MapvalidatedResults(result);

		if(errors != null) {
			return errors;
		}
		
		this.userService.saveUser(user);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
