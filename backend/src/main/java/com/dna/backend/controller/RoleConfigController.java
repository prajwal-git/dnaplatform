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

import com.dna.backend.modle.RoleConfig;
import com.dna.backend.repository.RoleConfigRepository;

@RequestMapping("/roleConfig")
@Controller
@CrossOrigin
public class RoleConfigController {

	@Autowired
	RoleConfigRepository roleConfigRepository;

	@GetMapping("/{id}")
	public RoleConfig getUser(@PathVariable Integer id) {
		return roleConfigRepository.findById(id).orElse(null);
	}

	@PostMapping("/")
	public RoleConfig postUser(@RequestBody RoleConfig roleConfig) {
		return roleConfigRepository.save(roleConfig);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		roleConfigRepository.deleteById(id);
	}

	@PutMapping("/")
	public RoleConfig putUser(@RequestBody RoleConfig roleConfig) {
		return roleConfigRepository.save(roleConfig);
	}

	@PatchMapping("/")
	public RoleConfig patchUser(@RequestBody RoleConfig roleConfig) {
		return roleConfigRepository.save(roleConfig);
	}

}
