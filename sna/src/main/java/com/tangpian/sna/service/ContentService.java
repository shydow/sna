package com.tangpian.sna.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangpian.sna.component.gplus.fetch.GplusFetcher;
import com.tangpian.sna.dao.ContentDao;
import com.tangpian.sna.dao.ProfileDao;
import com.tangpian.sna.dao.UserDao;
import com.tangpian.sna.model.Content;
import com.tangpian.sna.model.Profile;

@Service
public class ContentService {
	@Autowired
	private GplusFetcher gplusFetcher;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private ContentDao contentDao;

	public List<Content> updateContents(Profile profile) {
		if (null == profile.getFetchTime()
				|| oldThanNow(profile.getFetchTime()) > 7) {
			List<Content> contents = gplusFetcher.fetchContent(profile);
			contentDao.save(contents);
			profile.setFetchTime(new Date());
			profileDao.save(profile);
			return contents;
		}
		return null;
	}

	private long oldThanNow(Date past) {
		return (new Date().getTime() - past.getTime()) / (24 * 60 * 60 * 1000L);
	}

}
