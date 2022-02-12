package com.dna.backend.security;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.dna.backend.security.AppUserPermission.*;

public enum AppUserRole {
USER(Sets.newHashSet(USER_READ, USER_WRITE)),
STUDENT(Sets.newHashSet(USER_READ, USER_WRITE)),
ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));
	
	private final Set<AppUserPermission> permissions;

	 AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<AppUserPermission> getPermissions() {
		return permissions;
	}
	
	
}
