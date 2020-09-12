package com.dna.backend.modle;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
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

}
