package com.dna.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dna.backend.repository.RoleRepository;

@Controller
public class TemplateController {
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	} 

}