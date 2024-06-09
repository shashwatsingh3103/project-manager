package com.springboot.projectmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.issue;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.requests.issuerequest;
import com.springboot.projectmanager.service.issueservice;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api")
public class issuecontroller {

	
	@Autowired
	private issueservice issueservice;
	
	@Autowired
	private userservice userservice;
	
	@GetMapping("/issue/{id}")
	public ResponseEntity<issue> getbyid(@PathVariable("id") int id) throws Exception{
		Optional<issue> i = issueservice.getbyid(id);
		
		return new ResponseEntity<>(i.get(), HttpStatus.OK);
	}
	@GetMapping("/issue/project/{id}")
	public ResponseEntity<List<issue>> getbyprojectid(@PathVariable("id") int id) throws Exception{
		List<issue> i = issueservice.getissuebyprojectid(id);
		
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
	
	@PostMapping("/create-issue")
	public ResponseEntity<issue> createissue(@RequestBody issuerequest i  ) throws Exception{
		issue j= issueservice.createissue(i);
		return new ResponseEntity<>(j, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-issue/{id}")
	public ResponseEntity<String> delteissue(@PathVariable("id") int id ) throws Exception{
		issueservice.deleteissue(id);
		return new ResponseEntity<>("issue deleted succesfully", HttpStatus.OK);
	}
	
	@PutMapping("/add-user/{id}/{uid}")
	public ResponseEntity<issue> addusertoissue(@PathVariable("id") int id,@PathVariable("uid") int uid) throws Exception{
	
		issue i =issueservice.addusertoissue(id, uid);
		
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	@PutMapping("/update-status/{id}/{status}")
	public ResponseEntity<issue> updatestatustoissue(@PathVariable("id") int id,@PathVariable("status") String s) throws Exception{
	
		issue i =issueservice.updatestaus(id, s);
		
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
}
