package com.springboot.projectmanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.chat;
import com.springboot.projectmanager.model.message;
import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.chatrepository;
import com.springboot.projectmanager.repository.messagerepository;
@Service
public class messageserviceimpl implements messageservice {

	
	@Autowired
	private userservice userservice;
	@Autowired
	
	private projectservice projectservice;
	@Autowired
	private messagerepository messagerepository;
	
	@Autowired
	private chatrepository chatrepository;

	@Override
	public message sendmessage(int sendid, int projectid, String message) throws Exception {
		
		user sender = userservice.finduserbyid(sendid);
		
	chat c  = projectservice.getprojectbyid(projectid).getChat();
	
	message m = new message();
	m.setChat(c);
	m.setContent(message);
	m.setSender(sender);
	m.setCreatedat(LocalDateTime.now());
	c.getMessage().add(m);
		return  messagerepository.save(m);
	}

	@Override
	public List<message> getmessagebyid(int pid) throws Exception {
		chat c  = projectservice.getprojectbyid(pid).getChat();
		
	List<message> message=	c.getMessage();
		
		return message;
	}

	@Override
	public List<message> getmessagebychatid(int chatid) throws Exception {
		Optional<chat> c = chatrepository.findById(chatid);
		chat cc= c.get();
		if (cc==null) {
			throw new Exception ("chat not found");
		}
		return cc.getMessage() ;
	}

}
