package io.agileintelligence.ppmtool.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MapValidationService {
	
	public ResponseEntity<Map<String, String>> MapvalidatedResults(BindingResult result) {
		
		
		if (result.hasErrors()) {

			final Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().stream().forEach( F -> errors.put(F.getField(), F.getDefaultMessage()) );
			
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}

}
