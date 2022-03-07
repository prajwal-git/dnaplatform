package com.dna.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.modle.Meeting;
import com.dna.backend.repository.MeetingRepository;
import com.dna.backend.service.MeetingService;


@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	private MeetingRepository meetingRepo;
	
	@Autowired
	private MeetingService meetingService;
	
	@PostMapping("/saveMeeting")
	public void saveScheduledMeeting(@RequestBody Meeting meeting) {
		meetingRepo.save(meeting);
	}

	@GetMapping("/getMeeting")
	public List<Meeting> getAllMeetings() {
		return meetingService.getAllMeetings();
	}
	
	
}
