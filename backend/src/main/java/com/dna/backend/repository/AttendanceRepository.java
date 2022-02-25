package com.dna.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dna.backend.modle.Attendance;


public interface AttendanceRepository extends CrudRepository<Attendance,Long>{

}
