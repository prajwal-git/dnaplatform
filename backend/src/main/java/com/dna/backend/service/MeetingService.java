package com.dna.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dna.backend.modle.Meeting;
import com.dna.backend.repository.MeetingRepository;

@Component
public class MeetingService {

	@Autowired
	private MeetingRepository meetingRepo;
	
	public List<Meeting> getAllMeetings() {

		return (List<Meeting>) meetingRepo.findAll();
	}
	
	
	
	
	
}
