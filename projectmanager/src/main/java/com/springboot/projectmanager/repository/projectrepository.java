package com.springboot.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.projectmanager.model.project;
import com.springboot.projectmanager.model.user;

public interface projectrepository  extends JpaRepository<project, Integer>{

	
	List<project>findByOwner(user u );
	
	
	
	@Query("SELECT p FROM project p JOIN p.team t WHERE t = :u")
	List<project> findProjectByTeam(@Param("u") user u);

	
	List<project> findByTeamContainingOrOwner(user teamMember, user owner);
	
	 @Query("SELECT p FROM project p JOIN p.team t WHERE p.name LIKE %:name% AND t = :user")
	    List<project> findByNameContainingAndTeamContaining(@Param("name") String name, @Param("user") user user);
}
