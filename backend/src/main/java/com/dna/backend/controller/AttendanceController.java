package com.dna.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.repository.AttendanceRepository;

@RestController
@RequestMapping(value="/attendance")
public class AttendanceController {
	
	
	@Autowired
	private AttendanceRepository attendanceRepo;
	


}
