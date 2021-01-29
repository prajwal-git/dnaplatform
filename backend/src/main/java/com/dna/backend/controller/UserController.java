package com.dna.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;
import com.dna.backend.service.UserService;
import com.dna.backend.service.UserServiceImpl;

//www.google.com/user/1 ---- path param
@RequestMapping("/user")
@RestController

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
		return "redirect:/registration?success";
	}
	
	@GetMapping("/userCreation")
	public String getUserCreationForm() {
		return "userCreation";
	}

	@PostMapping("/createUser")
	public String createUser(@ModelAttribute("user") UserDto userDto) {
		userService.save(userDto);
		return "redirect:/user/userCreation?success";
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

	/*
	 * This is rest call which will upload data to User Model
	 * 
	 * @param MultipartFile
	 * 
	 * @return List<user>
	 * 
	 */
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/uploaddata")
	public List<User> uploaddata(@RequestParam("file") MultipartFile file) throws IOException {
		return (userServiceImpl).readFile(file);
	}
	
	/*
	 * This is rest call which will send CSV formatted data of User
	 * 
	 * @param none
	 * 
	 * @return ResponseEntity<Resource> , media type application/csv
	 * 
	 */
	
	@GetMapping("/exportrole")
	public ResponseEntity<Resource> getRoleCSV() throws IOException {
		InputStreamResource inuser;
		inuser = userServiceImpl.fileDownload();
		return ResponseEntity.ok() // checking status
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment ; role.csv")
				.contentType(MediaType.parseMediaType("application/csv")).body(inuser);
	}

}
