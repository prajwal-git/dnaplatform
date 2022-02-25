package com.dna.backend.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByFirstName(final String firstName);
	User findByemail(String email);
	
	
	/*
	 * A container object which may or may not contain a non-null value. If a value is present, isPresent()
	 *  returns true. If no value is present, the object is considered empty and isPresent() returns false.
	 *  */
	User save(UserDto userDto);
	Optional<User> findByUserName(final String userName);
	Optional<User> findByEmail(String userEmail);
	
	@Query("Select u.email from User u where u.email =:email")
	public String getEmail(@Param("email") String email);
	
}
