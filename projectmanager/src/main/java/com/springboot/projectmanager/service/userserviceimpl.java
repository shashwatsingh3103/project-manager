package com.springboot.projectmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.springboot.projectmanager.model.user;
import com.springboot.projectmanager.repository.userrepository;
import com.springboot.projectmanager.securityconfig.jwtprovider;

@Service
public class userserviceimpl  implements userservice{

	@Autowired
	userrepository userrepository;

	@Override
	public user finduserbyjwt(String jwt) throws Exception {
	String email = jwtprovider.getemailfromtoken(jwt);
		return finduserbyemail(email);
	}

	@Override
	public user finduserbyemail(String email) throws Exception {
		
				user u = userrepository.findByEmail(email);
				if (u==null) {
					throw new  Exception("user not found");
				}
				return u;
	}

	@Override
	public user finduserbyid(int id) throws Exception {
	Optional<user>u= userrepository.findById(id);
	if(u.isEmpty()) throw new  Exception("user not found");
		return u.get();
	}

	@Override
	public user updateuserprojectsize(user u, int size) throws Exception {
	u.setProjectsize(u.getProjectsize()+1);
		
		return userrepository.save(u);
	}
	
	
	
}
