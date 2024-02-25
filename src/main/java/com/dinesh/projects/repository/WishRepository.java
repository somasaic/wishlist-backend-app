package com.dinesh.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinesh.projects.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {

	List<Wish> findByUserId(Long userId);

}
