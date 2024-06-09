package com.springboot.projectmanager.model;



import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class comment {

	@Id
	@GeneratedValue
	private int id ;

	@ManyToOne
	private issue issue;
	
	private String content;
	
	private LocalDateTime createdat;
	
	@ManyToOne
	private user user ;
	
}
