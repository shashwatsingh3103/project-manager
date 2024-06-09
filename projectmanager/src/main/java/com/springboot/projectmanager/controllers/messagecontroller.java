package com.springboot.projectmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projectmanager.model.message;
import com.springboot.projectmanager.requests.messagerequest;
import com.springboot.projectmanager.service.chatservice;
import com.springboot.projectmanager.service.messageservice;
import com.springboot.projectmanager.service.projectservice;
import com.springboot.projectmanager.service.userservice;

@RestController
@RequestMapping("/api/message")
public class messagecontroller {

	@Autowired
	private userservice userservice;
	@Autowired
	private chatservice chatservice;
	@Autowired
	private messageservice messageservice;
	@Autowired
	private projectservice projectservice;
	
	@PostMapping("/send")
	
	ResponseEntity<message> sendmessage(@RequestBody messagerequest m )throws Exception {
		
		message n = messageservice.sendmessage(m.getSenderid(),m.getProjectid() ,m.getMesssage());
		return new  ResponseEntity<>(n,HttpStatus.OK);
	}
	
	@GetMapping("/chat/{pid}")
	ResponseEntity<List<message> >getmessage(@PathVariable int pid) throws Exception{
		List<message> m= messageservice.getmessagebyid(pid);
		return new ResponseEntity<List<message>>(m,HttpStatus.OK);
	}
	
	@GetMapping("/chat/chatid/{cid}")
	ResponseEntity<List<message> >getmessagefromchat(@PathVariable int cid) throws Exception{
		List<message> m= messageservice.getmessagebychatid(cid);
		return new ResponseEntity<List<message>>(m,HttpStatus.OK);
	}
	
}
