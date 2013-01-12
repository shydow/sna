package com.tangpian.sna.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tangpian.sna.model.FetchElement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class TestFetchElementDao {
	@Autowired
	FetchElementDao fetchElementDao;
	
	@Before
	public void before() {
		fetchElementDao.save(new FetchElement("aaa",1,1));
	}
	
	@Test
	public void test() {
		List<String> ids = new ArrayList<String>();
		List<FetchElement> fetchElements = fetchElementDao.list();
		for (FetchElement fetchElement : fetchElements) {
			System.out.println(fetchElement.getId() + "|" + fetchElement.getFetchTime());
			ids.add(fetchElement.getId());
		}
		System.out.println();
		fetchElementDao.updateFetchTime(ids.get(0));
		for (FetchElement fetchElement : fetchElements) {
			System.out.println(fetchElement.getId() + "|" + fetchElement.getFetchTime());
		}
	}
}
