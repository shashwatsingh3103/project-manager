package com.springboot.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class user {

	@Id
	@GeneratedValue
	private int  id ; 
	private String fullname ;
	private String email;
	private String password;
	private int projectsize;
	
	@OneToMany(mappedBy = "assigne" ,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<issue> assignedissue= new ArrayList<>();
	
	@OneToOne
	private subscription sub;
	
	
}
