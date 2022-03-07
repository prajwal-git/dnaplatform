package com.dna.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.modle.Meeting_Ref;
import com.dna.backend.repository.Meeting_RefRepository;

@RestController
@RequestMapping(value="/meeting_ref")
public class Meeting_RefController {
	
	@Autowired
	Meeting_RefRepository meetingRefRepo;

	@PostMapping(value="/saveAllMeetingRef")
	public void saveData(@RequestBody Meeting_Ref meeting_ref) {
		meetingRefRepo.save(meeting_ref);
		
	}
	
	@GetMapping(value="/getAllMeetingRef")
	List<Meeting_Ref> getAllDatas(){
		return (List<Meeting_Ref>) meetingRefRepo.findAll();
	}
	
	@GetMapping(value="/{batchName}")
	List<Meeting_Ref> getBatchDetails(@PathVariable String batchName){
		return (List<Meeting_Ref>) meetingRefRepo.getByBatchName(batchName);
	}
}
