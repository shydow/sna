package com.tangpian.sna.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangpian.sna.dao.UserDao;
import com.tangpian.sna.fetch.Fetcher;
import com.tangpian.sna.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Resource(name = "gplusFetcher")
	private Fetcher gplusFetcher;

	public void createUser(User user) {
		userDao.save(user);
	}

	public List<User> listAll() {
		Iterable<User> users = userDao.findAll();
		List<User> result = new ArrayList<User>();
		for (User user : users) {
			result.add(user);
		}
		return result;
	}
	
	public User find(String id) {
		return userDao.findOne(id);		
	}

//	public void FetchAndSave() {
//		List<User> users = gplusFetch();
//		userDao.save(users);
//	}

//	private List<User> gplusFetch() {
//
//		// gplus fetch!
//		List<FetchElement> fetchElements = fetchElementDao.listByTypeAndSource(
//				FetchElement.TYPE_USER, FetchElement.SOURCE_GPLUS);
//
//		List<String> candidateUserNos = new ArrayList<String>();
//		List<String> candidateFetchElementIds = new ArrayList<String>();
//		for (FetchElement fetchElement : fetchElements) {
//			if (null == fetchElement.getFetchTime()
//					|| (oldThanNow(fetchElement.getFetchTime()) > 7L)) {
//				candidateUserNos.add(fetchElement.getUserNo());
//				
//				fetchElementDao.updateFetchTime(fetchElement.getId());
//			}
//		}
//		
////		fetchElementDao.updateFetchTime(candidateFetchElementIds);
//		
//		return gplusFetcher.fetch(candidateUserNos.toArray(new String[candidateUserNos.size()]));
//	}

	private long oldThanNow(Date past) {
		return (new Date().getTime() - past.getTime()) / (24 * 60 * 60 * 1000L);
	}
}