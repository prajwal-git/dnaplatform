package com.dna.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dna.backend.modle.Role;
import com.dna.backend.modle.User;
import com.dna.backend.repository.RoleRepository;
import com.dna.backend.service.UserService;

import static com.dna.backend.dto.UserDto.DEFAULT_USER;

/**
 * @Controller for Role model with /role request mapping
 * @author radha
 *
 */
@RequestMapping("/role")
@Controller
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

	// Static default roles list
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
	public User addDefaultRolesAndUser() {

		// Adding default roles in role table
		for (Role r : DEFAULT_ROLES) {
			roleRepository.save(r);
		}
		
		//seting default role in userDto
		DEFAULT_USER.setRoles(DEFAULT_ROLES);

		return userService.save(DEFAULT_USER);
	}

}
