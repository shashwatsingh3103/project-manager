package com.springboot.projectmanager.service;

import com.springboot.projectmanager.model.Plantype;
import com.springboot.projectmanager.model.subscription;
import com.springboot.projectmanager.model.user;

public interface subscriptionservice {

	subscription createsubscription(user u);
	subscription getsubscription(int u) throws Exception;
	subscription upgradesubscription(int uid, Plantype p)throws  Exception;
	
	boolean isvalid(subscription s) ;
}
