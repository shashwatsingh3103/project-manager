package com.springboot.projectmanager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class message {

	@Id
	@GeneratedValue
	private int id ;
	
	private String content ; 
	
	private LocalDateTime createdat;
	
	@ManyToOne
	private chat chat;
	@ManyToOne
	private user sender;
}
