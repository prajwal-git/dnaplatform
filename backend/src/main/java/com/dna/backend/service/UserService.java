package com.dna.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.User;

public interface UserService extends UserDetailsService {
	User save(UserDto userDao);

	/*
	 * A container object which may or may not contain a non-null value. If a value
	 * is present, isPresent() returns true. If no value is present, the object is
	 * considered empty and isPresent() returns false.
	 */
	public Optional<User> findByUserName(String userName);

	public Optional<User> findByUserEmail(String email);

}
