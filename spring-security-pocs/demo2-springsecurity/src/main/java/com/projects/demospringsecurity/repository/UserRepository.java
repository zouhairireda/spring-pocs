package com.projects.demospringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.demospringsecurity.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
