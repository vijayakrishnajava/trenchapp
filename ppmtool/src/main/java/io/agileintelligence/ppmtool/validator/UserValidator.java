package io.agileintelligence.ppmtool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.agileintelligence.ppmtool.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		User user = (User) obj;
		
		if(user.getPassword().length() < 6) {
			errors.rejectValue("password", "","password length should be minimum 6");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "","password and confirm password should match");
		}
		
	}

}
