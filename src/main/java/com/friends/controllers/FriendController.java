package com.friends.controllers;

import java.util.List;

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
import com.friends.service.FriendService;

@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

	private final FriendService friendService;

	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}
	@GetMapping
	public List<Friend> findFriends(){
		return friendService.findAllFriends();
	}
	@GetMapping("/{id}")
	public Friend findFriendById(@PathVariable("id") Long id){
		return friendService.findFriend(id);
	}
	
	@DeleteMapping("/{id}")
	public Friend removeFriendBy(@PathVariable("id") long id){
		
		Friend friend= friendService.findFriend(id);
		friendService.removeFriend(id);
		return friend;
	}
	
	@PostMapping
	public Friend addFriend(@RequestBody Friend friend) {
		
		friendService.addFriend(friend);
		return friend;
	}
	
	@PutMapping("/{id}")
	public Friend updateFriend(@RequestBody Friend friend,@PathVariable("id") long id) {
		
		friendService.removeFriend(id);
		friendService.addFriend(friend);
		return friend;
	}
	
}
