package com.springboot.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.chat;

public interface chatrepository  extends JpaRepository<chat, Integer>{

}
