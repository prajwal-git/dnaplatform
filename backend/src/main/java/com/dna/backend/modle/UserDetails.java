package com.dna.backend.modle;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails implements Serializable {
	private final static long serialVersionUID = 7702L;
	private String userName;
	private String emailAddress;


	
	//@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	//@JsonPropertyOrder({ "serviceTypeId", "serviceName", "active" })
	
	
	
}
