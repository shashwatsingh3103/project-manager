package com.springboot.projectmanager.requests;

import lombok.Data;

@Data
public class messagerequest {

	private int senderid;
	private int projectid;
	private String messsage;
}
