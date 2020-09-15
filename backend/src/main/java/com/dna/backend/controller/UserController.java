package com.dna.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;
//www.google.com/user/1 ---- path param
@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired // DI from spring
	private UserRepository userRepository;
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public List<User> getUser(@PathVariable Integer id){
		return (List<User>) userRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/")
	public User postUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
//	@PutMapping("/")
//	public User putUser(@RequestBody User user) {
//		User oldUser = userRepository.findById(user.getClass()).orElse(null);
//		return userRepository.save(user);
//	}
	
	
}
