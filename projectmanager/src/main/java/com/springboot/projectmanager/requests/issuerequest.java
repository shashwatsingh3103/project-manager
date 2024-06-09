package com.springboot.projectmanager.requests;

import java.time.LocalDate;

import lombok.Data;
@Data
public class issuerequest {

	private String title;
	private String description;
	
	private String status ;
	private int projectid ;
	
	private String priority;
	
	private LocalDate duedate;
	

	
}
