package com.springboot.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.userrepository;

@Service
public class customuserdetailsimpl implements UserDetailsService {

	@Autowired
	private userrepository userrepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		user u = userrepository.findByEmail(username);
		if (u==null) {
			throw new UsernameNotFoundException("user does not exist with the email : "+username);
		}
		List<GrantedAuthority> auth= new ArrayList<>();
		
		return new User(u.getEmail(), u.getPassword(),auth);
	}

	
}
