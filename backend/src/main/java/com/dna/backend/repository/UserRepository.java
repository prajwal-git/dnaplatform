package com.dna.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dna.backend.modle.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(final String userName);

	User findByEmail(String email);

}
