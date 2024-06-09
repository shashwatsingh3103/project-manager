package com.springboot.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.invitation;

public interface invitationrepository  extends JpaRepository<invitation, Integer>{

	invitation findByEmail(String email);
	
	invitation findByToken(String token);
}
