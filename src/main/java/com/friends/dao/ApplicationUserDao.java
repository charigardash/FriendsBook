package com.friends.dao;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ApplicationUserDao {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
