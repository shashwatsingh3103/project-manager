package com.springboot.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.subscription;
import com.springboot.projectmanager.model.user;

public interface subscriptionrepository extends JpaRepository<subscription, Integer> {

	subscription findByUser(user u);
}
