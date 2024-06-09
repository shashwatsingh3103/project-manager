package com.springboot.projectmanager.service;

import java.util.List;

import com.springboot.projectmanager.model.comment;

public interface commentservice {

	
	comment createcomment(int issued , int uid, String comment) throws Exception;
	void deletecomment(int id , int uid)throws Exception;
	List<comment> findbyissueid(int issueid) throws Exception;
	
}
