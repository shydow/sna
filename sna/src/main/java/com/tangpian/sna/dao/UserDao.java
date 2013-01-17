package com.tangpian.sna.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangpian.sna.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
	public User findByUsername(String username);
	
	public boolean existbyUsername(String username);
}