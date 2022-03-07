package com.dna.backend.modle;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meeting_Ref {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="meetingref_seq" )
	@SequenceGenerator(name="meetingref_seq",sequenceName = "meetingref_seq",allocationSize=1)
	private long id;
	
	private String batchName;
	
	@Column(name = "start_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "end_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private int totalStudents;
	
	//@Convert(converter = JpaConverterJson.class)
	@Lob
    private List<UserDetails> details;

	@OneToOne
	@JoinColumn(name="meeting_id")
	private Meeting meetingId;
	
}
