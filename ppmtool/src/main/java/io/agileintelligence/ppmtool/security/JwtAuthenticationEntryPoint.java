package io.agileintelligence.ppmtool.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.agileintelligence.ppmtool.exception.InvalidLoginResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		
		InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
		invalidLoginResponse.setUsername("Invalid username");
		invalidLoginResponse.setPassword("Invalid password");
		
		String jsonResponse = new Gson().toJson(invalidLoginResponse);
		
		arg1.getWriter().print(jsonResponse);
		arg1.setStatus(401);
		
		
	}
}
