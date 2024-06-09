package com.springboot.projectmanager.model;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class chat {
	@Id
	@GeneratedValue
	private int  id ; 
	
	@OneToOne
	private project project;
	
	private String name ;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "chat" , orphanRemoval = true)
	private List<message> message = new ArrayList<>();
	
	@ManyToMany
	private List<user> user = new ArrayList<>();
}
