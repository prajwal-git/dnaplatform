package com.dna.backend.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;


public interface UserService{
	User findByUserName(String userName);
	
	User save(UserRepository repository);

}
