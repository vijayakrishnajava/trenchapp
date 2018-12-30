package io.agileintelligence.ppmtool.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.agileintelligence.ppmtool.SecurityConstants;
import io.agileintelligence.ppmtool.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenGenerator {
	
	public String generateToken(Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", user.getUsername());
		claims.put("fullname", user.getFullName());
		
		Date now = new Date(System.currentTimeMillis());
		Date expTime = new Date(now.getTime() + SecurityConstants.TOKEN_EXP_TIME);
		
		return Jwts.builder().
			setSubject(user.getUsername()).
			setIssuedAt(now).
			setExpiration(expTime).
			signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).
			setClaims(claims).compact();
		
	}
	
	
	public boolean validateToken(String token) {
		
		if (StringUtils.hasText(token) && token.contains(SecurityConstants.TOKEN_PREFIX)) {
			String realtoken = token.substring(7, token.length());

			try {
				Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(realtoken);
				return true;
			}
			catch (ExpiredJwtException e) {
				System.out.println("JWT parser error occured ExpiredJwtException : "+e.getMessage());
			}

			catch (MalformedJwtException e) {
				System.out.println("JWT parser error occured MalformedJwtException : "+e.getMessage());
			}

			catch (SignatureException e) {
				System.out.println("JWT parser error occured SignatureException : "+e.getMessage());
			}

			catch (IllegalArgumentException e) {
				System.out.println("JWT parser error occured IllegalArgumentException : "+e.getMessage());
			}

			
		}
		
		return false;
	}
	
	public Claims getClaimsFromToken(String token) {
		System.out.println(token);
		
		String realtoken = token.substring(7, token.length());
		System.out.println(realtoken);

		Claims claims = (Claims)Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(realtoken).getBody();
		return claims;
	}
	
	public String getUsername(String token) {
		Claims claims = this.getClaimsFromToken(token);
		return (String)claims.get("username");
	}
	
}