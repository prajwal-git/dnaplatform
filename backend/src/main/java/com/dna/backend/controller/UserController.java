package com.dna.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;
//www.google.com/user/1 ---- path param
@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
	final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired // DI from spring
	private UserRepository userRepository;
	
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		logger.info("Get method");
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Integer id){
		return userRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/")
	public User postUser(@RequestBody User user) {
		return userRepository.save(user);

	}
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		 userRepository.deleteById(id);
	}
	
//	@PutMapping("/")
//	public User putUser(@RequestBody User user) {
//		User user = userRepository.findById(user.getId()).orElse(null);
//		
//		
//	}
	
	
}
