package com.springboot.projectmanager.securityconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class tokenvalidatorfilter  extends OncePerRequestFilter{

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		String jwt=request.getHeader(jwtconstant.JWT_HEADER);
		
		if (jwt!=null) {
			 try {
				 
				 String email=jwtprovider.getemailfromtoken(jwt);
				 
				List< GrantedAuthority> authority = new ArrayList<>();
				Authentication auth =new UsernamePasswordAuthenticationToken(email,null, authority);
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				throw new BadCredentialsException("invalid token ");
			}
			 
		}
		
		filterChain.doFilter(request, response);
		
	}


		
	

}
