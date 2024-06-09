package com.springboot.projectmanager.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.Plantype;
import com.springboot.projectmanager.model.subscription;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.subscriptionrepository;

@Service
public class subscriptionserviceimpl  implements subscriptionservice{

	@Autowired
	private subscriptionrepository subscriptionrepository;
	@Autowired
	private userservice userservice;
	@Override
	public subscription createsubscription(user u) {
		
		subscription s = new subscription();
		s.setUser(u);
		s.setIsvalid(true);
		s.setPlantype(Plantype.FREE);
		s.setStartdate(LocalDateTime.now());
		s.setEnddate(LocalDateTime.now().plusMonths(12));
		return subscriptionrepository.save(s);
	}

	@Override
	public subscription getsubscription(int u) throws Exception {
	user uu = userservice.finduserbyid(u);
		subscription  s= subscriptionrepository.findByUser(uu);
		if (!isvalid(s)) {
			s.setPlantype(Plantype.FREE);
			s.setEnddate(LocalDateTime.now().plusMonths(12));
			s.setStartdate(LocalDateTime.now());
		}
		return subscriptionrepository.save(s);
	
	
	}

	@Override
	public subscription upgradesubscription(int uid, Plantype p) throws Exception {
	
		subscription s= getsubscription(uid);
		s.setPlantype(p);
		s.setStartdate(LocalDateTime.now());
		if(p.equals(Plantype.ANNUALLY) ){
			s.setEnddate(LocalDateTime.now().plusMonths(12));
			
		}
		else if(p==Plantype.MONTHLY) {
			s.setEnddate(LocalDateTime.now().plusMonths(1));
		}
	
	
	
		return subscriptionrepository.save(s);
	}

	@Override
	public boolean isvalid(subscription s) {
	
		if(s.getPlantype().equals(Plantype.FREE) ){
		return true;
	}
		else {
			if(LocalDateTime.now().isAfter(s.getEnddate())){
				return false;
			}
			
			return true;
				
		
		}
		

}}
