package com.springboot.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.issue;

public interface issuerepository extends JpaRepository<issue, Integer> {

	List<issue> findByProjectid(int id);
}
