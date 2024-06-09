package com.springboot.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.projectmanager.model.comment;
import com.springboot.projectmanager.model.issue;

public interface commentrepository  extends JpaRepository<comment, Integer>{

List<comment>	findByIssue(issue i);
}
