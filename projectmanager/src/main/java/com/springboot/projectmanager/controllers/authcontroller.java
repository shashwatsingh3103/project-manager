package com.springboot.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.userrepository;
import com.springboot.projectmanager.requests.loginrequest;
import com.springboot.projectmanager.response.authresponse;
import com.springboot.projectmanager.securityconfig.jwtprovider;
import com.springboot.projectmanager.service.customuserdetailsimpl;
import com.springboot.projectmanager.service.subscriptionservice;

@RestController
@RequestMapping("/auth")
public class authcontroller {

	@Autowired
	private userrepository userrepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private subscriptionservice subscriptionservice;
	
	@Autowired
	private customuserdetailsimpl customuserdetailsimpl;
	
	@PostMapping("/signup")
	public ResponseEntity<authresponse> signup(@RequestBody user u ) throws Exception {
		
		user eu = userrepository.findByEmail(u.getEmail());
		if ( eu !=null) {
			new Exception("user alredy exists with email :"+u.getEmail());
		}
		user newu= new user();
		newu.setEmail(u.getEmail());
		newu.setFullname(u.getFullname());
		newu.setPassword(passwordEncoder.encode(u.getPassword()));
		newu.setProjectsize(u.getProjectsize());
	
		user s = userrepository.save(newu);
		subscriptionservice.createsubscription(s);
	
		Authentication auth = new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtprovider.generatetoken(auth);
		
		return new ResponseEntity<>(new authresponse(jwt, "succesfully creeated account"), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<authresponse> signin(@RequestBody loginrequest u ) throws Exception {
		
		String username = u.getEmail();
		String password= u.getPassword();
		
		
		
		Authentication auth = authenticate(username , password);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtprovider.generatetoken(auth);
		
		return new ResponseEntity<>(new authresponse(jwt, "succesfully signin account"), HttpStatus.CREATED);
		
	}

	private Authentication authenticate(String username, String password) throws Exception {
		UserDetails userDetails = customuserdetailsimpl.loadUserByUsername(username);
		if ( userDetails==null) {
			throw new Exception("user does not exist with email :"+username);
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())){
		
		throw new Exception("invalid password ");
	}
	return new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities() );}
}
