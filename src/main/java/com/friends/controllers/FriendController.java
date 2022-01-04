package com.friends.controllers;

import java.security.Principal;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.friends.entity.Friend;
import com.friends.entity.User;
import com.friends.repository.UserRepository;
import com.friends.service.FriendService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/friend")
public class FriendController  {

	private final FriendService friendService;
	private final UserRepository userRepository;
	

	public FriendController(FriendService friendService,UserRepository userRepository) {
		this.friendService = friendService;
		this.userRepository = userRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Set<Friend> findFriends(Principal principal){
		String name=principal.getName();
		User user = userRepository.findByUsername(name)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));
		return  user.getFriends();
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Friend findFriendById(@PathVariable("id") Long id){
		return friendService.findFriend(id);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public Friend removeFriendBy(@PathVariable("id") long id){
		
		Friend friend= friendService.findFriend(id);
		friendService.removeFriend(id);
		return friend;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Friend addFriend(@RequestBody Friend friend, Principal principal) {
		String name=principal.getName();
		User user = userRepository.findByUsername(name)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));
		user.getFriends().add(friend);
		userRepository.save(user);
		System.out.println("Added to data base");
		return friend;
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public Friend updateFriend(@RequestBody Friend friend,@PathVariable("id") long id) {
		
		friendService.removeFriend(id);
		friendService.addFriend(friend);
		return friend;
	}
	
	
	
}
