package com.tangpian.sna.fetch;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tangpian.sna.fetch.gplus.GplusFetcher;
import com.tangpian.sna.model.User;
import com.tangpian.sna.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class TestGplusFetcher {
	@Autowired
	GplusFetcher fetcher;

	@Autowired
	UserService userService;


	@Test
	public void test() {
		List<User> users = fetcher.fetch("111081291678895561458");
		for (User user : users) {
			System.out.println(user.getNickname());
		}
	}


}
