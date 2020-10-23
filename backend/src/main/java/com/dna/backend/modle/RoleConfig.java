package com.dna.backend.modle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Role_Config")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoleConfig {
	@Id
	@GeneratedValue
	private int roleConfigId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "roleId", nullable=false)
	private Role roleId;
	
	@Column(nullable=false)
	private String roleName;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "actionId", nullable=false)
	private UserActions actionId;
	
	@Column(nullable=false)
	private String actionName;
}
