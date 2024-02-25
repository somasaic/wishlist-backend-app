package com.dinesh.projects.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dinesh.projects.entity.AppUser;
import com.dinesh.projects.entity.Role;
import com.dinesh.projects.repository.AppUserRepository;
import com.dinesh.projects.repository.RoleRepository;

@Service
public class AppUserService {
	@Autowired
	private AppUserRepository repository;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private AuthenticationManager authenticationManager;

	public String signUp(Map<String, String> requestMap) {
		AppUser user = requestMapToAppUser(requestMap);
		if (repository.existsByUsername(user.getUsername())) {
			return "Username already exists";
		}
		Role userRole = roleRepo.findByName("USER").get();
		user.setRoles(Collections.singleton(userRole));
		repository.save(user);
		return "User Registered successfully";
	}

	private AppUser requestMapToAppUser(Map<String, String> requeMap) {
		AppUser user = new AppUser();
		user.setUsername(requeMap.get("username"));
		user.setPassword(requeMap.get("password"));
		return user;
	}

	public Boolean login(Map<String, String> credentials) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.get("username"), credentials.get("password"));
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		return authentication.isAuthenticated();

	}

}
