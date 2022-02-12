package com.dna.backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import org.springframework.web.multipart.MultipartFile;
import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;
import com.dna.backend.service.UserService;
import com.dna.backend.service.UserServiceImpl;

//www.google.com/user/1 ---- path param
@RequestMapping("/user")
@Controller

public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

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

	/*
	 * we can register new user through this method
	 * 
	 * @valid annotation will brought the validation we have created in Dto class.
	 * binding result will bind the error result if it occur and whatever message
	 * has been given in the variable of Dto class will display. else part display
	 * the message. if userName and email can not use twice. these field required
	 * unique
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(
					result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())
							+ "",
					HttpStatus.BAD_REQUEST);
		} else {
			return userService.findByUserName(userDto.getUserName())
					.map(a -> new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST))
					.orElseGet(() -> userService.findByUserEmail(userDto.getEmail())
							.map(u -> new ResponseEntity<>("email already in use", HttpStatus.BAD_REQUEST))
							.orElseGet(() -> {
								userService.save(userDto);

								// Send user able to register email or other return new
								return new ResponseEntity<>("User created", HttpStatus.CREATED);
							}));

		}
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
