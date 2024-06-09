package com.springboot.projectmanager.service;

public interface emailservice {

	void sendemail(String email, String link) throws Exception;
}
