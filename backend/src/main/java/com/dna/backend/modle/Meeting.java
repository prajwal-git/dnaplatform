package com.dna.backend.modle;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="meeting_seq" )
	@SequenceGenerator(name="meeting_seq",sequenceName = "meeting_seq",allocationSize=1)
	private Long id;
	
	private String meetingName;
	
	@JsonFormat(locale = "en_US", timezone = "CST", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
    
    
	public Meeting() {
		super();
	}


	public Meeting(Long id, String meetingName, Date time) {
		super();
		this.id = id;
		this.meetingName = meetingName;
		this.time = time;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMeetingName() {
		return meetingName;
	}


	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}



}
