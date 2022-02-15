package com.dna.backend.modle;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	private String officePhone;
	private String cellPhone;
	private Date dob;
	private boolean isActive;
	private String gender;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
	private Collection<Role> roles;
//constructor
	public User(String userName, String firstName, String lastName, String middleName, String email, String password,
			String address, String officePhone, String cellPhone, Date dob, boolean isActive, String gender,
			Collection<Role> roles) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.officePhone = officePhone;
		this.cellPhone = cellPhone;
		this.dob = dob;
		this.isActive = isActive;
		this.gender = gender;
		this.roles = roles;
	}


}
