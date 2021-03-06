package com.tangpian.sna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangpian.sna.component.gplus.domain.GplusProfile;
import com.tangpian.sna.dao.ProfileDao;
import com.tangpian.sna.dao.UserDao;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.model.User;

@Service
public class ProfileService {

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private UserDao userDao;

	public void save(String account,String userId, int type) {
		User user = userDao.findOne(userId);
		Profile profile = null;
		switch (type) {
		case GplusProfile.TYPE:
			profile = new GplusProfile();
			profile.setAccount(account);
			profile.setUser(user);
			break;

		default:
			break;
		}

		if (null != profile) {
			profileDao.save(profile);
		}
	}
	
	public Profile find(String userId, int type) {
		return profileDao.findByUserIdAndType(userId, type);
	}
	
	public List<Profile> find(String userId) {
		return profileDao.findByUserId(userId);
	}
}
