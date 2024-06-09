package com.springboot.projectmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.chat;
import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.chatrepository;
import com.springboot.projectmanager.repository.projectrepository;

@Service
public class projectserviceimpl implements projectservice {

	@Autowired
	private projectrepository projectrepository;
	@Autowired
	private userservice userservice;
	@Autowired
	private chatservice chatservice;
	
	
	@Override
	public project createproject(project p, user u) throws Exception {
	
		  project n = new project();
		    n.setName(p.getName());
		    n.setOwner(u);
		    n.setDescription(p.getDescription());
		    n.setTags(p.getTags());
		    n.setCatogery(p.getCatogery());
		    n.getTeam().add(u);

		    // Create and save the chat entity first
		    chat c = new chat();
		    chat savedChat = chatservice.createchat(c); // Ensure createchat saves the chat entity
		    n.setChat(savedChat);

		    // Save the project entity with the associated chat
		    project savedProject = projectrepository.save(n);
		    savedChat.setProject(savedProject); // Ensure the chat has the reference to the saved project

		    return savedProject;
		
		
		
	}

	@Override
	public List<project> projectbyteam(user user, String catogery, String tag) throws Exception {
	List<project> pl = projectrepository.findByTeamContainingOrOwner(user, user);
	
	if (catogery!=null) {
		pl=pl.stream().filter(p ->p.getCatogery().equals(catogery))
		.collect(Collectors.toList());
	}
	
	if (tag!=null) {
		pl=pl.stream().filter(p ->p.getTags().contains(tag))
		.collect(Collectors.toList());
	}
	
		return pl;
	}

	@Override
	public project getprojectbyid(int id) throws Exception {
Optional<project> p = projectrepository.findById(id);
		if(p.isEmpty()) {
			throw new Exception("project does not exist");
		}
		return p.get();
	}

	@Override
	public void deleteproject(int pid, int uid) throws Exception {
	
		user u = userservice.finduserbyid(uid);
		project p = getprojectbyid(pid);
		if(p.getOwner().equals(u)) {
			projectrepository.delete(p);
		}
		
	}

	@Override
	public project updateproject(project p, int pid) throws Exception {
	
		project n = getprojectbyid(pid);
		n.setName(p.getName());
	
		n.setDescription(p.getDescription());
		n.setTags(p.getTags());
		n.setCatogery(p.getCatogery());
	
		
		return projectrepository.save(n);
	}

	@Override
	public void addusertoproject(int pid, int uid) throws Exception {
		
		project p = getprojectbyid(pid);
		user u = userservice.finduserbyid(uid);
		if(!p.getTeam().contains(u)) {
		p.getTeam().add(u);
		p.getChat().getUser().add(u);
		projectrepository.save(p);}
		
	}

	@Override
	public void removeusertoproject(int pid, int uid) throws Exception {
		project p = getprojectbyid(pid);
		user u = userservice.finduserbyid(uid);
		if(p.getTeam().contains(u)) {
			p.getChat().getUser().remove(u);
			p.getTeam().remove(u);
			projectrepository.save(p);
		}
		throw new  Exception("user doesnot exist in this project");
		
		
	}

	@Override
	public chat getchatbyprojectid(int pid) throws Exception {
		project p = getprojectbyid(pid);
		
		return p.getChat();
	}

	@Override
	public List<project> searchproject(String keyword, user u) throws Exception {
		
		String partialname= "%"+keyword+"%";
		
		return projectrepository.findByNameContainingAndTeamContaining(partialname, u);
	}

}
