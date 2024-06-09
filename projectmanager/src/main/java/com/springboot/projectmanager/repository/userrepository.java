package com.springboot.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.user;

public interface userrepository extends JpaRepository<user, Integer> {

	user findByEmail(String email);
}
