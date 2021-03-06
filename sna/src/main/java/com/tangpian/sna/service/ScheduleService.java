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
public class ScheduleService {
	@Autowired
	private GplusFetcher gplusFetcher;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private ContentDao contentDao;

	public void bulkUpdate() {
		List<Profile> profiles = profileDao.findAll();

		updateContents(profiles);
	}

	private void updateContents(List<Profile> profiles) {
		for (Profile profile : profiles) {
			updateContents(profile);
		}
	}

	public List<Content> updateContents(Profile profile) {
		List<Content> contents = gplusFetcher.fetchContent(profile);
		contentDao.save(contents);
		profile.setFetchTime(new Date());
		profileDao.save(profile);
		return contents;
	}
	
	private long oldThanNow(Date past) {
		return (new Date().getTime() - past.getTime()) / (24 * 60 * 60 * 1000L);
	}

}
