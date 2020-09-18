package com.dna.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@Component
public class UserController {

	@Autowired // DI from spring
	private UserRepository userRepository;
	private User Abc;
	
	
	@GetMapping("/")
	public List<User> getAllUsers(){
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
	@PatchMapping("/")
	public User patUser (@RequestBody User user) {
		return userRepository.save(user);
	}
	
	/*
	 * @Bean("/") public User getUser(@RequestBody User user) { return
	 * userRepository.save(user);
	 */
	
	public void display() {
		System.out.println("this is display");
		
		
	}
	
	
	
	
//	@PutMapping("/")
//	public User putUser(@RequestBody User user) {
//		User user = userRepository.findById(user.getId()).orElse(null);
//		
//		
//	}
	
	
}
