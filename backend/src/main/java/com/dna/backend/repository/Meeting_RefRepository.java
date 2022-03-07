package com.dna.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dna.backend.modle.Meeting_Ref;

@Repository
public interface Meeting_RefRepository extends CrudRepository<Meeting_Ref,Long> {

	@Query("select m from Meeting_Ref m Where m.batchName =:batchName")
	List<Meeting_Ref> getByBatchName(@Param("batchName") String batchName);

	
}
