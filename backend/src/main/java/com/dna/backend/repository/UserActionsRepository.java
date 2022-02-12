package com.dna.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dna.backend.modle.UserActions;

@Repository
public interface UserActionsRepository extends JpaRepository<UserActions, Integer> {
}