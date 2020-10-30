package com.dna.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dna.backend.modle.UserActions;
import com.dna.backend.repository.UserActionsRepository;

@RequestMapping("/UserActions")
@Controller
@CrossOrigin
public class UserActionsController {

	@Autowired
	UserActionsRepository userActionsRepository;

	@GetMapping("/{id}")
	public UserActions getUser(@PathVariable Integer id) {
		return userActionsRepository.findById(id).orElse(null);
	}

	@PostMapping("/")
	public UserActions postUser(@RequestBody UserActions userActions) {
		return userActionsRepository.save(userActions);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userActionsRepository.deleteById(id);
	}

	@PutMapping("/")
	public UserActions putUser(@RequestBody UserActions userActions) {
		return userActionsRepository.save(userActions);
	}

	@PatchMapping("/")
	public UserActions patchUser(@RequestBody UserActions userActions) {
		return userActionsRepository.save(userActions);
	}

}
