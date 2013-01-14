package com.tangpian.sna.fetch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tangpian.sna.component.gplus.fetch.GplusFetcher;
import com.tangpian.sna.model.Profile;
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
		Profile profile = fetcher.fetchProfile("111081291678895561458");
		System.out.println(profile.getAccount());
	}

}
