package com.projects.demospringsecurity.service;

import com.projects.demospringsecurity.domain.User;

public interface UserService {
	
    public void save(User user);

    public User findByUsername(String username);
}
