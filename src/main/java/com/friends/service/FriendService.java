package com.friends.service;

import java.util.List;

import com.friends.entity.Friend;

public interface FriendService {

	List<Friend> findAllFriends();
	void addFriend(Friend friend);
	Friend findFriend(long id);
	void removeFriend(long id);
	List<Friend> findFriend(String keyWord);
}
