package com.springboot.projectmanager.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.invitation;
import com.springboot.projectmanager.repository.invitationrepository;

@Service
public class invitationserviceimpl implements invitationservice{
	
	
	@Autowired
	private emailservice emailservice;
	
	@Autowired
	private invitationrepository invitationrepository;
	
	
	
	

	@Override
	public void sendinvitation(String email, int pid) throws Exception {
	
		String intoken =UUID.randomUUID().toString();
		invitation in= new invitation();
		in.setEmail(email);
		in.setProjectid(pid);
		in.setToken(intoken);
		
	invitationrepository.save(in);
	String link= "http://localhost:5173/accept_invitation?token="+intoken;
		
		emailservice.sendemail(email, link);
	}

	@Override
	public invitation acceptinvitation(String token)throws Exception {
	invitation i = invitationrepository.findByToken(token);
	if(i==null) {
		throw new Exception("token not found or inavild token");
	}
		return i;
	}

	@Override
	public String gettokenbyusermail(String mail) throws Exception {
	
		invitation i = invitationrepository.findByEmail(mail);
		return i.getToken();
	}

	@Override
	public void deletetoken(String token)throws Exception  {
	
		invitation i = invitationrepository.findByToken(token);
		invitationrepository.delete(i);
		
	}

}
