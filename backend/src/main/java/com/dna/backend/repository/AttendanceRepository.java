package com.dna.backend.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dna.backend.modle.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance,Long>{

	@Query("Select a.emailAddress from Attendance a where a.emailAddress =:emailAddress and a.meetingDate =:meetingDate and a.groupName =:groupName")
	public String getEmail(@Param("emailAddress") String emailAddress, @Param("meetingDate") Date meetingDate, @Param("groupName") String groupName);
}
