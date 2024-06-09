package com.springboot.projectmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api")
public class usercontroller {
	@Autowired
	private userservice userservice;
	
	@GetMapping("/user")
	ResponseEntity<user> searchprojects(
			@RequestHeader("Authorization") String jwt) throws Exception{
		user u = userservice.finduserbyjwt(jwt);
	
		
	return  new ResponseEntity<>(u,HttpStatus.OK);
}
	
}
