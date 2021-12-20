package com.friends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friends.entity.Friend;
import com.friends.repository.FriendRepository;

@Service
public class FriendServiceImp implements FriendService {
	
	private final FriendRepository friendRepository;
	
	

	@Autowired
	public FriendServiceImp(FriendRepository friendRepository) {
		this.friendRepository = friendRepository;
	}



	@Override
	public List<Friend> findAllFriends() {
		// TODO Auto-generated method stub
		return friendRepository.findAll();
	}



	@Override
	public void addFriend(Friend friend) {
		// TODO Auto-generated method stub
		friendRepository.save(friend);
	}



	@Override
	public Friend findFriend(long id) {
		// TODO Auto-generated method stub
		Optional<Friend> friend= friendRepository.findById(id);
		if(friend.isPresent()) {
			return friend.get();
		}
		
		// TODO add Exception
		
		return null;
	}



	@Override
	public void removeFriend(long id) {
		// TODO Auto-generated method stub
		friendRepository.deleteById(id);
	}

}
