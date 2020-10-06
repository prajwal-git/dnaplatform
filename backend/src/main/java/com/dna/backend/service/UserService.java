package com.dna.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.User;

public interface UserService extends UserDetailsService{
	User save(UserDto userDao);
}
