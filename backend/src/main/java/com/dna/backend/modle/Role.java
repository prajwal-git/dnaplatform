package com.dna.backend.modle;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private int roleId;

	@Column(nullable = false)
	private String roleName;

	@OneToMany(mappedBy = "roleId")
	private Set<RoleConfig> roleConfig;

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

}
