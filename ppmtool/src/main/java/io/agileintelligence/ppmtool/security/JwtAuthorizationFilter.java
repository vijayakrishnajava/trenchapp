package io.agileintelligence.ppmtool.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.agileintelligence.ppmtool.SecurityConstants;
import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.exception.TokenExpirationException;
import io.agileintelligence.ppmtool.service.UserService;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenGenerator JwtTokenGenerator;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
		boolean isValid = JwtTokenGenerator.validateToken(authHeader);

		if (isValid) {
			String username = JwtTokenGenerator.getUsername(authHeader);
			User user = this.userService.getUser(username);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
					null, Collections.EMPTY_LIST);
			
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			
		} else {
			//throw new TokenExpirationException("Token has expired.", authHeader);
		}
		
		filterChain.doFilter(request, response);
		
	}
}
