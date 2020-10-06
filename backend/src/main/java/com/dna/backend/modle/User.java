package com.dna.backend.modle;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

public class User {

	@Id
	@GeneratedValue
	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String password;
	private String address;
	private int officePhone;
	private int cellPhone;
	private Date dob;
	private boolean isActive;
	private char gender;
	private String role;
	private boolean enable;
	
}


