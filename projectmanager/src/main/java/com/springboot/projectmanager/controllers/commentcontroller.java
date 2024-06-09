package com.springboot.projectmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.comment;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.requests.commentrequest;
import com.springboot.projectmanager.service.commentservice;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api")
public class commentcontroller {

	@Autowired
private commentservice commentservice;
	@Autowired
	private userservice userservice;
	
	
	@PostMapping("/createcomment")
	
	public ResponseEntity<comment> createcomment(@RequestHeader("Authorization") String jwt , @RequestBody commentrequest req) throws Exception{
		
		user u = userservice.finduserbyjwt(jwt);
		comment c = commentservice.createcomment(req.getIssueid(), u.getId(),req.getContent() );
		return new ResponseEntity<comment>(c, HttpStatus.ACCEPTED);
	}
	
@DeleteMapping("/deletecomment/{id}")
	
	public ResponseEntity<String> deletecomment(@RequestHeader("Authorization") String jwt , @PathVariable int id) throws Exception{
		
		user u = userservice.finduserbyjwt(jwt);
		 commentservice.deletecomment(id, u.getId());
		return new ResponseEntity<>("succesfully deleted comment", HttpStatus.ACCEPTED);
	}
@GetMapping("/issue-comment/{id}")
public ResponseEntity<List<comment>> getissuecomment(@PathVariable int id) throws Exception{

	List<comment> c= commentservice.findbyissueid(id);
	return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
}

}
