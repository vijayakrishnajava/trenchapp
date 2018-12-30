package io.agileintelligence.ppmtool.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.service.MapValidationService;
import io.agileintelligence.ppmtool.service.ProjectService;
import io.agileintelligence.ppmtool.service.UserService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	MapValidationService mapValidationService;
	
	@Autowired
	UserService userService;
	
	@PostMapping
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		
		ResponseEntity<?> errors = this.mapValidationService.MapvalidatedResults(result);
		
		if (errors != null) {
			return errors;
		}
		
		Project response = this.projectService.saveOrUpdateProject(project, this.userService.getUserFromSecurityContext());
		return new ResponseEntity<Project>(response, HttpStatus.CREATED);
	
	}
	
	
	@GetMapping("/{identifier}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> findProject(@PathVariable("identifier") String identiier) {
		
		Project project = this.projectService.findProject(identiier,this.userService.getUserFromSecurityContext());
		
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> findAll() {

		return new ResponseEntity<>(this.projectService.findAll(this.userService.getUserFromSecurityContext()), HttpStatus.OK); 
		
	}
	
	@DeleteMapping("/{identifier}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteProject(@PathVariable String identifier) { 

		this.projectService.deleteProject(identifier, this.userService.getUserFromSecurityContext());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
