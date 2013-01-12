package com.tangpian.sna.fetch;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tangpian.sna.fetch.gplus.GplusFetcher;
import com.tangpian.sna.model.FetchElement;
import com.tangpian.sna.model.User;
import com.tangpian.sna.service.AdminService;
import com.tangpian.sna.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class TestGplusFetcher {
	@Autowired
	GplusFetcher fetcher;

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

//	@Test
//	public void test() {
//		List<User> users = fetcher.fetch("111081291678895561458");
//		for (User user : users) {
//			System.out.println(user.getNickname());
//		}
//	}

	@Test
	public void testUserService() {
		adminService.addUserFetchElement(new FetchElement(
				"111081291678895561458", FetchElement.TYPE_USER,
				FetchElement.SOURCE_GPLUS));
		userService.FetchAndSave();
		List<User> users = userService.listAll();
		for (User user : users) {
			System.out.println(user.getUsername());
		}
	}
}
