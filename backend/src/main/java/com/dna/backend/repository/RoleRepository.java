package com.dna.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dna.backend.modle.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRoleName(final String roleName);
}