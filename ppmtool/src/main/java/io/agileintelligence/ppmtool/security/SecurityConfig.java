package io.agileintelligence.ppmtool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.agileintelligence.ppmtool.SecurityConstants;
import io.agileintelligence.ppmtool.service.CustomUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	CustomUserService customUserService;
	
	@Bean
	public JwtAuthorizationFilter getJwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}
	
/*	@Autowired
	JwtAuthorizationFilter jwtAuthorizationFilter;
*/	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
		and().
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().
		headers().frameOptions().sameOrigin().
		and().
		authorizeRequests().antMatchers("/",
										"/favicon.ice",
										"/**/*.png",
										"/**/*.gif",
										"/**/*.jpeg",
										"/**/*.html",
										"/**/*.css",
										"/**/*.js").permitAll().
		antMatchers(HttpMethod.POST, SecurityConstants.SING_UP_URLS).permitAll().
		antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_URL).permitAll().
		antMatchers(HttpMethod.POST, SecurityConstants.VALIDATE_URL).permitAll().
		antMatchers("/h2-console/**").permitAll().
		anyRequest().authenticated();
		
		http.addFilterBefore(this.getJwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService).passwordEncoder(this.bCryptPasswordEncoder());
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager getAutheticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
