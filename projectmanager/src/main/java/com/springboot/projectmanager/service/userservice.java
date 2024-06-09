package com.springboot.projectmanager.service;

import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.user;


public interface userservice {

	user finduserbyjwt(String jwt)throws Exception;
	
	user finduserbyemail(String email) throws Exception;
	
	user finduserbyid(int id) throws Exception;
	
	user updateuserprojectsize(user u , int size) throws Exception;
	
}
