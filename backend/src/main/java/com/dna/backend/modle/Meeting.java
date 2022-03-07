package com.dna.backend.modle;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="meeting_seq" )
	@SequenceGenerator(name="meeting_seq",sequenceName = "meeting_seq",allocationSize=1)
	private Long meetingId;
	
	private String meetingName;
	
	@JsonFormat(locale = "en_US", timezone = "CST", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	
    
    
	

	

}
