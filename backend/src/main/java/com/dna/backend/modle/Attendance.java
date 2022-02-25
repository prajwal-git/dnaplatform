package com.dna.backend.modle;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="attendance_seq" )
	@SequenceGenerator(name="attendance_seq",sequenceName = "attendance_seq",allocationSize=1)
	private long id;
	
	private String userName;
	
	@JsonFormat(locale = "en_US", timezone = "CST", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date meetingDate;
	
	@JsonFormat(locale = "en_US", timezone = "CST", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;
	
	@JsonFormat(locale = "en_US", timezone = "CST", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logoutTime;

	public Attendance(String userName, Date meetingDate, Date loginTime, Date logoutTime) {
		super();
		this.userName = userName;
		this.meetingDate = meetingDate;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	
	

	
}
