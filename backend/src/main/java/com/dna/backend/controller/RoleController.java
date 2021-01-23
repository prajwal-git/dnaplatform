package com.dna.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.Role;
import com.dna.backend.repository.RoleRepository;
import com.dna.backend.service.RoleService;
import com.dna.backend.service.UserService;

/**
 * @Controller for Role model with /role request mapping
 * @author radha
 *
 */
@RequestMapping("/role")
@RestController
public class RoleController {
	/**
	 * injecting role repository with default autowiring(by type) with default scope
	 * (singleton)
	 */
	@Autowired
	RoleRepository roleRepository;
	/**
	 * injecting userservice with default autowiring(by type) with default scope
	 * (singleton)
	 */
	@Autowired
	UserService userService;

	/**
	 * injecting roleService with default autowiring(by type) with default scope
	 * (singleton)
	 */
	@Autowired
	RoleService roleService;
	
// Static default roles list
	@SuppressWarnings("serial")
	private final static List<Role> DEFAULT_ROLES = new ArrayList<Role>() {
		{
			add(new Role("Admin"));
			add(new Role("SuperAdmin"));
			add(new Role("HR"));
			add(new Role("Student"));
		}
	};

	/**
	 * Default constructor for thymleaf usage
	 */
	public RoleController() {
		super();
	}

	/**
	 * Adding default role and InstanceAdmin in system
	 *
	 * @return List<Role>
	 */
	@PostMapping("/defaultRoles")
	public void addDefaultRolesAndUser() {
// Adding default roles in role table
		for (Role r : DEFAULT_ROLES) {
			roleRepository.save(r);
		}

//seting default role in userDto

		userService.save(new UserDto("InstanceAdmin", "InstanceAdmin", "InstanceAdmin", "InstanceAdmin",
				"zorbasofted@gmail.com", "password", "Test", Integer.parseInt("123"), Integer.parseInt("123"),
				new Date(), true, '\u0000', DEFAULT_ROLES));
	}
	
	/*
	 * This is rest call which will upload data to ROLE Model
	 * 
	 * @param MultipartFile
	 * 
	 * @return List<Role>
	 * 
	 */

	@PostMapping("/uploaddata")
	public List<Role> uploaddata(@RequestParam("file") MultipartFile file) throws IOException {
		return roleService.readFile(file);
	}

	/*
	 * This is rest call which will send CSV formatted data of ROLE
	 * 
	 * @param none
	 * 
	 * @return ResponseEntity<Resource> , media type application/csv
	 * 
	 */

	@GetMapping("/exportrole")
	public ResponseEntity<Resource> getRoleCSV() throws IOException {
		InputStreamResource inrole;
		inrole = roleService.fileDownload();
		return ResponseEntity.ok() // checking status
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment ; role.csv")
				.contentType(MediaType.parseMediaType("application/csv")).body(inrole);
	}
}