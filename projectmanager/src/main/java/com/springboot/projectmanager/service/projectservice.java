package com.springboot.projectmanager.service;

import java.util.List;

import com.springboot.projectmanager.model.chat;
import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;

public interface projectservice {

	
	project createproject(project p ,user u) throws Exception;
	
	List<project> projectbyteam(user user ,String catogery, String tag ) throws Exception;
	
	project getprojectbyid(int id )throws Exception;
	
	void deleteproject(int pid, int uid)throws Exception;
	
	project updateproject(project updatedproject , int pid) throws Exception;
	
	void addusertoproject(int pid, int uid) throws Exception;
	
	void removeusertoproject(int pid, int uid) throws Exception;
	
	chat getchatbyprojectid(int pid) throws Exception;
	List<project> searchproject(String keyword , user u)throws Exception;
}
