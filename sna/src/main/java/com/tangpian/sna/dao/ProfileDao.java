package com.tangpian.sna.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tangpian.sna.model.Profile;
import com.tangpian.sna.model.User;

public interface ProfileDao extends JpaRepository<Profile, String> {
	public List<Profile> findByType(int type);

	public Profile findByUserAndType(User user, int type);
	
	public Profile findByUserIdAndType(String userId, int type);
}
