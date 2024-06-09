package com.springboot.projectmanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.comment;
import com.springboot.projectmanager.model.issue;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.commentrepository;

@Service
public class commentserviceimpl implements commentservice {

	@Autowired
	private issueservice issueservice;
	@Autowired
	private userservice userservice;
	@Autowired
	private commentrepository commentrepository;
	@Override
	public comment createcomment(int issued, int uid, String comment) throws Exception {
	
		Optional<issue> ii = issueservice.getbyid(issued);
		issue i = ii.get();
		user u = userservice.finduserbyid(uid);
		comment c = new comment();
		c.setContent(comment);
		c.setIssue(i);
		c.setUser(u);
		c.setCreatedat(LocalDateTime.now());
		comment save= commentrepository.save(c);
		i.getComments().add(save);
		return save;
		
	
	}

	@Override
	public void deletecomment(int id, int uid) throws Exception {

		user u = userservice.finduserbyid(uid);
		Optional<comment> c= commentrepository.findById(id);
		if (c.isEmpty()) {
			throw new Exception("cannot delete comment");
		}
		
		comment cc= c.get();
		if (cc.getUser().equals(u)) {
		commentrepository.delete(cc);}
	
	}

	@Override
	public List<comment> findbyissueid(int issueid) throws Exception {
	Optional<issue> i = issueservice.getbyid(issueid);
	issue ii= i.get();
	
		return commentrepository.findByIssue(ii);
	}

}
