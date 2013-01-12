package com.tangpian.sna.dao;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tangpian.sna.model.User;

@Repository
public class UserDao {

	@Autowired
	private PersistenceManagerFactory persistenceManagerFactory;

	public void add(User user) {
		persistenceManagerFactory.getPersistenceManager().makePersistent(user);
	}

	public void add(User... users) {
		persistenceManagerFactory.getPersistenceManager().makePersistentAll(
				users);
	}

	public void add(List<User> users) {
		persistenceManagerFactory.getPersistenceManager().makePersistentAll(
				users);
	}

	public List<User> list() {
		Query q = persistenceManagerFactory.getPersistenceManager().newQuery(
				User.class);
		return (List<User>) q.execute();
	}

}