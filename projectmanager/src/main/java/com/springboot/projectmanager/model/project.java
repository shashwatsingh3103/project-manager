package com.springboot.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class project {

	

	@Id
	@GeneratedValue
	private int  id ; 
	private String name ;
	private String description;
	private String catogery;
	
	private List<String > tags=  new ArrayList<>();
	
	
	@JsonIgnore
	@OneToOne(mappedBy = "project" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private chat chat ;
	
	@ManyToOne
	private user owner ;
	
	@OneToMany(mappedBy = "project" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<issue> issues= new ArrayList<>();
	
	@ManyToMany
	private List<user> team = new ArrayList<>();
	
	
}
