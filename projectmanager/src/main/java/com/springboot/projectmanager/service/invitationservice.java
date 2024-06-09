package com.springboot.projectmanager.service;

import com.springboot.projectmanager.model.invitation;

public interface invitationservice {

	public void sendinvitation(String email ,  int pid) throws Exception;
	public invitation acceptinvitation(String token )throws Exception;
	
	public String gettokenbyusermail(String mail)throws Exception;;
	
	public void deletetoken(String token)throws Exception;;
	
}
