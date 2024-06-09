package com.springboot.projectmanager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class subscription {

	@Id
	@GeneratedValue
	private int  id ; 
	
	private LocalDateTime  startdate;
	private LocalDateTime  enddate;
	
	@OneToOne
	private user user;
	
	private boolean isvalid;
	private Plantype plantype;
	
}
