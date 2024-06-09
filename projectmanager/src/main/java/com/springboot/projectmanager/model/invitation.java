package com.springboot.projectmanager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class invitation {
	@Id
	@GeneratedValue
	private int id ;
	
	private String token ;
	
	private String email;
	
	private int projectid;
}
