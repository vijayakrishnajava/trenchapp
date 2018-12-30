package io.agileintelligence.ppmtool.security;

public class LoginResponse {

	private boolean isSucess;
	private String jwttoken;
	
	public String getJwttoken() {
		return jwttoken;
	}
	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	public boolean isSucess() {
		return isSucess;
	}
	public void setSucess(boolean isSucess) {
		this.isSucess = isSucess;
	}
	
	
}
