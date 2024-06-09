package com.springboot.projectmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.chat;
import com.springboot.projectmanager.model.invitation;
import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.requests.inviterequest;
import com.springboot.projectmanager.service.invitationservice;
import com.springboot.projectmanager.service.projectservice;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api/projects")
public class projectcontroller {

	@Autowired
	private projectservice projectservice;
	
	@Autowired
	private userservice userservice;
	
	@Autowired
	private invitationservice invitationservice;
	
	@GetMapping
	ResponseEntity<List<project>> getprojects(@RequestParam(required = false) String tags,
@RequestParam(required = false) String catogery ,
@RequestHeader("Authorization") String jwt) throws Exception{
		
		user u = userservice.finduserbyjwt(jwt);
		List<project> p = projectservice.projectbyteam(u, catogery, tags);
		
	return  new ResponseEntity<>(p,HttpStatus.ACCEPTED);
}
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<project> getprojectsbyid(@PathVariable("id")int id ,
@RequestHeader("Authorization") String jwt) throws Exception{
		
	
		project p = projectservice.getprojectbyid(id);
		
	return  new ResponseEntity<>(p,HttpStatus.ACCEPTED);
}
	
	
	
	@PostMapping("/createproject")
	ResponseEntity<project> createprojects(@RequestBody project p ,
@RequestHeader("Authorization") String jwt) throws Exception{
		user u = userservice.finduserbyjwt(jwt);

		project pp = projectservice.createproject(p, u);
		
	return  new ResponseEntity<>(pp,HttpStatus.OK);
}
	@PatchMapping("/update/{id}")
	ResponseEntity<project> updateprojects(@RequestBody project p ,
@PathVariable("id") int id ) throws Exception{
	

		project pp = projectservice.updateproject(p, id);
		
	return  new ResponseEntity<>(pp,HttpStatus.OK);
}
	
	
	@DeleteMapping("delete/{pid}")
	ResponseEntity<String> deleteprojects(@PathVariable("pid") int pid ,
			@RequestHeader("Authorization") String jwt) throws Exception{
		user u = userservice.finduserbyjwt(jwt);
	

	 projectservice.deleteproject(pid, u.getId());
		
	return  new ResponseEntity<>("deletd sucessfully",HttpStatus.OK);
}
	
	
	@GetMapping("/search")
	ResponseEntity<List<project>> searchprojects(@RequestParam(required = false)  String tags,
			@RequestHeader("Authorization") String jwt) throws Exception{
		user u = userservice.finduserbyjwt(jwt);
	

		List<project> pp = projectservice.searchproject(tags,u);
		
	return  new ResponseEntity<>(pp,HttpStatus.OK);
}
	
	
	@GetMapping("/chat{id}")
	ResponseEntity<chat> getchatbyprojectid(@PathVariable("id") int id) throws Exception{
		
	
		chat p = projectservice.getchatbyprojectid(id);
		
	return  new ResponseEntity<>(p,HttpStatus.ACCEPTED);
}
	
	
	@PostMapping("/invite")
	ResponseEntity<String> sendjoinlink(
@RequestBody  inviterequest i 
			
			) throws Exception{
	
invitationservice.sendinvitation(i.getEmail(), i.getProjectid());
		
	return  new ResponseEntity<>("inviation send sucessfully",HttpStatus.OK);
}
	
	
	
	@GetMapping("/accept-invite")
	ResponseEntity<invitation> acceptjoinlink(
@RequestParam String token,
@RequestHeader("Authorization") String jwt
			
			) throws Exception{
	
		user u = userservice.finduserbyjwt(jwt);
		
invitation i = invitationservice.acceptinvitation(token) ;
projectservice.addusertoproject(i.getProjectid(), u.getId());
		
	return  new ResponseEntity<>(i,HttpStatus.OK);
}
	
}
				
			


