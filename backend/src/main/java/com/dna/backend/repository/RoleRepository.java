package com.dna.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dna.backend.modle.Role;
import com.dna.backend.modle.User;

public interface RoleRepository extends JpaRepository<Role, Integer>  {
Role findByrole_id(final Integer role_id);
Role findByrole_name(final String role_name);
}