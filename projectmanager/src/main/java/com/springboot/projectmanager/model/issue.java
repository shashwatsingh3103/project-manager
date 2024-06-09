package com.springboot.projectmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class issue {

	@Id
	@GeneratedValue
	private int id ;
	
	@ManyToOne
	private user assigne;
	
	@ManyToOne
	@JsonIgnore
	private project  project ;
	
	private String title;
	private String description;
	
	private String status ;
	private int projectid ;
	
	private String priority;
	
	private LocalDate duedate;
	private List<String> tags= new ArrayList<>();
	@OneToMany(mappedBy = "issue" , orphanRemoval = true ,cascade = CascadeType.ALL )
	@JsonIgnore
	private List<comment> comments= new ArrayList<>();
	
	
	
}
