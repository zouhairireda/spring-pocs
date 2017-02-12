package com.projects.demospringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.demospringsecurity.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
