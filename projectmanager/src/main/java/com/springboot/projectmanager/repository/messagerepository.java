package com.springboot.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.message;

public interface messagerepository  extends JpaRepository<message, Integer>{

}
