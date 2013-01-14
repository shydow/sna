package com.tangpian.sna.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tangpian.sna.component.gplus.domain.GplusProfile;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class TestProfileDao {
	@Autowired
	ProfileDao profileDao;

	@Autowired
	UserDao userDao;

	@Before
	public void init() {
		User user = new User();
		user.setEmail("aa@aa.com");
		user.setUsername("aaaaa");
		userDao.save(user);

		GplusProfile gplusProfile = new GplusProfile();
		gplusProfile.setAccount("aaa");
		gplusProfile.setBiography("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		gplusProfile.setUser(user);
		profileDao.save(gplusProfile);
	}

	@Test
	public void testList() {
		Iterable<Profile> p = profileDao.findAll();
		for (Profile profile : p) {
			System.out.println(profile.getAccount() + "|"
					+ profile.getUser().getEmail() + "|" + profile.getType());
		}
	}
	
	@Test
	public void test() {
		List<User> users = userDao.findAll();
		Profile profile = profileDao.findByUserAndType(users.get(0), 1);
		System.out.println("****************************" + profile.getAccount());
	}
	
	@Test
	public void test2() {
		List<User> users = userDao.findAll();
		Profile profile = profileDao.findByUserIdAndType(users.get(0).getId(), 1);
		System.out.println("#############################" + profile.getAccount());
	}
}
