package com.springboot.projectmanager.service;

import java.util.List;
import java.util.Optional;

import com.springboot.projectmanager.model.issue;
import com.springboot.projectmanager.requests.issuerequest;

public interface issueservice {

	Optional<issue> getbyid(int id ) throws Exception ;
	List<issue> getissuebyprojectid(int id)  throws Exception ;
	
	issue createissue(issuerequest req )throws Exception ;
	issue updateissue(int id , issuerequest req , int uid) throws Exception ;
	void deleteissue(int id ) throws Exception ;
	
	
	issue addusertoissue(int  iid, int uid )throws Exception ;
	
	issue updatestaus (int iid, String status)throws Exception ;
	
	
}
