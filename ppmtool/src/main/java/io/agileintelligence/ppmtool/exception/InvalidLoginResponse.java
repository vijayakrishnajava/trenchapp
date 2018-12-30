package io.agileintelligence.ppmtool.exception;

public class InvalidLoginResponse {

	private String username;
	private String password;
	
	public InvalidLoginResponse() {
	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
