package com.dna.backend.dto;

import java.util.Collection;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dna.backend.modle.Role;
//update
/* used some annotation for the purpose of server side validation. In registration , 
 * some field required fill with size of character and message will display if not fill properly*/
public class UserDto {

	@NotEmpty(message = "User name can not be empty")
	@Size(min = 3, max = 20, message = "Length of userName must be between 3<20")
	private String userName;

	@NotNull(message = "First name can not be empty")
	@Size(min = 3, max = 20, message = "First name shoud be 3<20")
	private String firstName;

	@NotEmpty(message = "Last name can not be empty")
	private String lastName;

	private String middleName;

	@Email(message = "email format required")
	@NotEmpty(message = "email can not be empty")
	private String email;
	private String password;
	private String address;
	private String officePhone;
	private String cellPhone;
	private Date dob;
	private boolean isActive;
	private Character gender;
	private Collection<Role> roles;

	public UserDto() {
		super();
	}

	public UserDto(String userName, String firstName, String lastName, String middleName, String email, String password,
			String address, String officePhone, String cellPhone, Date dob, boolean isActive, Character gender,
			Collection<Role> roles) {
		// super();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}