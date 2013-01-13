package com.tangpian.sna.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tangpian.sna.model.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
	
}