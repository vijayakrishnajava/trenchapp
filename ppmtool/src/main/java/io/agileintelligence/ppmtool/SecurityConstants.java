package io.agileintelligence.ppmtool;

public class SecurityConstants {

	public static final String SING_UP_URLS = "/registration";
	public static final String LOGIN_URL = "/login";
	public static final String SECRET_KEY = "SecretKeyToGenJWTs";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final long TOKEN_EXP_TIME = 30000;
	public static final String VALIDATE_URL = "/validate";
}
