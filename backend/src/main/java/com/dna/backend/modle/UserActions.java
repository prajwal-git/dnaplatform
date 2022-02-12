package com.dna.backend.modle;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="User_Actions")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserActions {
	@Id
	@GeneratedValue
	private int actionId;
	
	@Column(nullable=false)
	private String actionName;
	
	@OneToMany(mappedBy="actionId")
	private Set<RoleConfig> roleConfig;
}
