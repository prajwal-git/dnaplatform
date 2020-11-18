package com.dna.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;

@Component
public class UsersCache {

	@Autowired
	UserRepository userRepository;
	
	@org.springframework.cache.annotation.Cacheable(value = "usersCache", key = "#firstName")
	public User getUser(String firstName) {
		System.out.println("Retriving from " + firstName);
		return userRepository.findByFirstName(firstName);
	}
	
	

}
