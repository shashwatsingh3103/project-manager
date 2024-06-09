package com.springboot.projectmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.chat;
import com.springboot.projectmanager.repository.chatrepository;

@Service
public class chatserviceimpl  implements chatservice{

	@Autowired
	private chatrepository chatrepository;
	@Override
	public chat createchat(chat chat) {
	
		return chatrepository.save(chat);
	}

}
