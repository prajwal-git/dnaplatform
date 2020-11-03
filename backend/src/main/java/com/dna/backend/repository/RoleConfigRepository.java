package com.dna.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dna.backend.modle.RoleConfig;

@Repository
public interface RoleConfigRepository extends JpaRepository<RoleConfig, Integer> {
}