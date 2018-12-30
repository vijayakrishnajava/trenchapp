package io.agileintelligence.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.service.ProjectTaskService;
import io.agileintelligence.ppmtool.service.UserService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("http://localhost:3000")
public class ProjectTaskController {

	@Autowired
	ProjectTaskService projectTaskService;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/{projectIdentifier}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getAll(@PathVariable("projectIdentifier") String projectIdentifier) {
		
		List<ProjectTask> tasks = this.projectTaskService.findTasksByIdentifier(projectIdentifier, this.userService.getUserFromSecurityContext());
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@PostMapping("/{projectIdentifier}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> save(@PathVariable("projectIdentifier") String projectIdentifier,
			@RequestBody @Valid ProjectTask projectTask, BindingResult result) {
		
		
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().stream().forEach( E -> errors.put(E.getField(), E.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask projectTask2 = this.projectTaskService.saveProjectTask(projectIdentifier, projectTask, this.userService.getUserFromSecurityContext());
		return new ResponseEntity<>(projectTask2, HttpStatus.CREATED);
	}

	@PutMapping("/{projectIdentifier}/{projectseq}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> update(@PathVariable("projectIdentifier") String projectIdentifier, @PathVariable("projectseq") String projectseq,
			@RequestBody @Valid ProjectTask projectTask, BindingResult result) {
		
		
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().stream().forEach( E -> errors.put(E.getField(), E.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		projectTask.setProjectSequnce(projectseq);
		
		ProjectTask projectTask2 = this.projectTaskService.saveProjectTask(projectIdentifier, projectTask, this.userService.getUserFromSecurityContext());
		return new ResponseEntity<>(projectTask2, HttpStatus.CREATED);
	}

	
	@GetMapping("/{projectIdentifier}/{projectSeq}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getTask(@PathVariable("projectIdentifier") String projectIdentifier,
			@PathVariable("projectSeq") String projectSeq) {
		
		ProjectTask task = this.projectTaskService.findTaskByProjectSequence(projectIdentifier, projectSeq, this.userService.getUserFromSecurityContext());
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectIdentifier}/{projectSeq}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> deleteTask(@PathVariable("projectIdentifier") String projectIdentifier,
			@PathVariable("projectSeq") String projectSeq) {
		
		this.projectTaskService.deleteTask(projectIdentifier,projectSeq,this.userService.getUserFromSecurityContext());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/statuses")
	public Map<String, String> getStatus() {
		
		Map<String, String> result = new HashMap<>();
	    result.put("TO_DO","TO DO");
	    result.put("IN_PROGRESS","IN PROGRESS");
	    result.put("DONE", "DONE");
		
		return result;
		
	}

	@GetMapping("/priorities")
	public Map<String, String> getPriorities() {
		
		Map<String, String> result = new HashMap<>();
	    result.put("1","High");
	    result.put("2","Medium");
	    result.put("3", "Low");
		
		return result;
		
	}

}
