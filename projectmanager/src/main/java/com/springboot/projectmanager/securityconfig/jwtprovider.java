package com.springboot.projectmanager.securityconfig;

import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class jwtprovider {

	
	static SecretKey key = Keys.hmacShaKeyFor(jwtconstant.SECRET_KEY.getBytes());

	public static String generatetoken(Authentication auth) {
		
	String jwt = Jwts.builder().setIssuedAt(new Date())
			.setExpiration(new Date(new Date().getTime()+8400000))
			.claim("email", auth.getName())		
			.signWith(key)
			.compact();
			return jwt;
	
	}
	
	public static String getemailfromtoken(String jwt) {
		
		
		
		
		
		jwt= jwt.substring(7);
		Claims claim = Jwts.parser()
				.setSigningKey(key).build()
				.parseClaimsJws(jwt)
				.getBody();
		String email= String.valueOf(claim.get("email"));
		return email;
				
		
		

	}
	
	
}
