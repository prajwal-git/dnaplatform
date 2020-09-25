package com.dna.backend.modle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private int role_id;
	private String role_name;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional=false)

	
	private User user;

}
