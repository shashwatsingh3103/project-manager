package com.springboot.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.Plantype;
import com.springboot.projectmanager.model.subscription;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.service.subscriptionservice;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api")
public class subscriptioncontroller {

	@Autowired
	private subscriptionservice subscriptionservice;
	
	@Autowired
	private userservice userservice;
	
	@GetMapping("/subscription/user")
	ResponseEntity<subscription> getusersubscription( @RequestHeader("Authorization") String jwt) throws Exception{
		user u = userservice.finduserbyjwt(jwt);
		subscription s = subscriptionservice.getsubscription(u.getId());
		return new ResponseEntity<subscription>(s,HttpStatus.OK);
	}
	
	@PatchMapping("/subscription/update")
	ResponseEntity<subscription> updateusersubscription( @RequestHeader("Authorization") String jwt , @RequestParam Plantype p) throws Exception{
		user u = userservice.finduserbyjwt(jwt);
		subscription s = subscriptionservice.upgradesubscription(u.getId(), p);
		return new ResponseEntity<subscription>(s,HttpStatus.OK);
	}
	
}
