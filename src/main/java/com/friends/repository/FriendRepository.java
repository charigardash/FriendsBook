package com.friends.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friends.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	public List<Friend> findByNameContaining(String name);
}
