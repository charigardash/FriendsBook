package com.friends.dao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.friends.entity.User;


public class ApplicationUser implements UserDetails{

	
	private static final long serialVersionUID = 1L;
	private final Collection<? extends GrantedAuthority> grantedAuthorities;
	private final Long id;
	
	@JsonIgnore
	private final String password;
	
	private final String username;
	
	private final String email;
	
	private final boolean isAccountNonExpired;
	
	private final boolean isAccountNonLocked;
	
	private final boolean isCredentialsNonExpired;
	
	private final boolean isEnabled;
	public ApplicationUser(
			Long id,
			String password, 
			String username,
			String email,
			Collection<? extends GrantedAuthority> grantedAuthorities,
			boolean isAccountNonExpired, 
			boolean isAccountNonLocked,
			boolean isCredentialsNonExpired,
			boolean isEnabled) {
		this.id=id;
		this.grantedAuthorities = grantedAuthorities;
		this.password = password;
		this.username = username;
		this.email = email;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}
	public static ApplicationUser build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new ApplicationUser(
				user.getId(), 
				user.getPassword(), 
				user.getUsername(), 
				user.getEmail(),
				authorities,
				true,
				true,
				true,
				true);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

	public String getEmail() {
		return email;
	}
	public Long getId() {
		return id;
	}

}
