package com.dinesh.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinesh.projects.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String name);
	Boolean existsByUsername(String name);
}
