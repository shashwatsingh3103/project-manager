package com.springboot.projectmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.issue;
import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.issuerepository;
import com.springboot.projectmanager.repository.userrepository;
import com.springboot.projectmanager.requests.issuerequest;

@Service
public class issueserviceimpl implements issueservice {
@Autowired
private userservice userservice;
	@Autowired
	private issuerepository issuerepository;
	@Autowired
	private projectservice projectservice;
	@Override
	public Optional<issue> getbyid(int id) throws Exception {
	
		Optional<issue> i =issuerepository.findById(id);
		if(i.isPresent()) {
			return i ;
		}
	throw new Exception("no issue found with id :"+id);
	}

	@Override
	public List<issue> getissuebyprojectid(int id) throws Exception {
		
		
		return issuerepository.findByProjectid(id);
	}

	@Override
	public issue createissue(issuerequest req) throws Exception {
		
		project p = projectservice.getprojectbyid(req.getProjectid());
		
		
		issue n = new issue();
		n.setTitle(req.getTitle());
		n.setDescription( req.getDescription());
		n.setDuedate( req.getDuedate());
		n.setPriority( req.getPriority());
		n.setStatus( req.getStatus());
		n.setProjectid( req.getProjectid());
		n.setProject(p);
		issuerepository.save(n);
		
		
		return issuerepository.save(n);
	}

	@Override
	public issue updateissue(int id, issuerequest req, int uid) throws Exception {
	Optional<issue> i = issuerepository.findById(id);
	issue n= i.get();
	n.setTitle(req.getTitle());
	n.setDescription( req.getDescription());
	n.setDuedate( req.getDuedate());
	n.setPriority( req.getPriority());
	n.setStatus( req.getStatus());
	n.setProjectid( req.getProjectid());
	
	issue ii = issuerepository.save(n);
	return ii;
	}

	@Override
	public void  deleteissue(int id) throws Exception {

		Optional<issue> i = getbyid(id);
	issuerepository.delete(i.get());
	}

	@Override
	public issue addusertoissue(int iid, int uid) throws Exception {
		user u =  userservice.finduserbyid(uid);
		Optional<issue> i = issuerepository.findById(iid);
		issue j = i.get();
		j.setAssigne(u);
		
		
		return issuerepository.save(j);
	}

	@Override
	public issue updatestaus(int iid, String status) throws Exception {
		Optional<issue> i = issuerepository.findById(iid);
		issue j = i.get();
		j.setStatus(status);
		return issuerepository.save(j);
	}

}
