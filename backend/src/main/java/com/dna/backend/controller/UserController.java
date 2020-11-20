package com.dna.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;
import com.dna.backend.service.UserService;

//www.google.com/user/1 ---- path param
@RequestMapping("/user")
@Controller
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	public UserController() {
		super();
	}

	public UserController(UserService userService) {
		super();
		this.userService = userService;      
	}

	@ModelAttribute("user")
	public UserDto UserDto() {
		return new UserDto();
	}

	@GetMapping("/registration")
	public String getRegistrationForm() {
		return "registration";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UserDto userDto) {
		userService.save(userDto);
		return "redirect:/user/registration?success";
	}

	@Autowired // DI from spring
	private UserRepository userRepository;

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Integer id) {
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

	@PutMapping("/")
	public User putUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PatchMapping("/")
	public User patchUser(@RequestBody User user) {
		return userRepository.save(user);
	}

}
