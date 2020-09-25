package com.dna.backend.security;

public enum AppUserPermission {
	USER_READ("user:read"),
	USER_WRITE("user:write");
	
	private final String permission;
	
	 AppUserPermission(String permission) {
		this.permission = permission;
	
	 }

	public String getPermission() {
		return permission;
	}
	 
	 
}
