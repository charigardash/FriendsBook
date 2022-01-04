package com.friends.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


import com.google.common.collect.Sets;
import static com.friends.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
	
	private final Set<ApplicationUserPermission> getPermissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> getPermissions) {
		this.getPermissions = getPermissions;
	}

	public Set<ApplicationUserPermission> getGetPermissions() {
		return getPermissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
	   Set<SimpleGrantedAuthority> permissionSet= getPermissions.stream()
			   .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			   .collect(Collectors.toSet());
	   permissionSet.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
	   return permissionSet;
	}
}
