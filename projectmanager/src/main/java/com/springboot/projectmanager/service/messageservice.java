package com.springboot.projectmanager.service;

import java.util.List;

import com.springboot.projectmanager.model.message;

public interface messageservice  {

	message sendmessage(int sendid, int chatid, String message) throws Exception;
	List<message> getmessagebyid(int pid) throws Exception;
	List<message>getmessagebychatid(int chatid) throws Exception;
}
