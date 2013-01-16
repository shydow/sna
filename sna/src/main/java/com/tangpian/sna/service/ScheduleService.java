package com.tangpian.sna.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangpian.sna.component.gplus.fetch.GplusFetcher;
import com.tangpian.sna.dao.ContentDao;
import com.tangpian.sna.dao.ProfileDao;
import com.tangpian.sna.dao.RelationDao;
import com.tangpian.sna.dao.UserDao;
import com.tangpian.sna.fetch.Fetcher;
import com.tangpian.sna.model.Content;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.model.Relation;

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

	@Autowired
	private RelationDao relationDao;

	public void bulkUpdate() {
		List<Profile> profiles = profileDao.findAll();

		updateContentsAndRelations(profiles);
	}

	private void updateContentsAndRelations(List<Profile> profiles) {
		for (Profile profile : profiles) {
			Map result = gplusFetcher.fetchContentAndRelation(profile);
			List<Content> contents = (List<Content>) result
					.get(GplusFetcher.TYPE_CONTENT);
			List<Relation> relations = (List<Relation>) result
					.get(GplusFetcher.TYPE_RELATION);
			contentDao.save(contents);
			relationDao.save(relations);
			profile.setFetchTime(new Date());
		}
		profileDao.save(profiles);
	}

	private void updateProfiles(List<Profile> profiles) {
		// TODO

		// for (Profile profile : profiles) {
		// if (profile.getFetchTime() == null
		// || oldThanNow(profile.getFetchTime()) >= 7) {
		//
		// }
		// }

	}

	private long oldThanNow(Date past) {
		return (new Date().getTime() - past.getTime()) / (24 * 60 * 60 * 1000L);
	}

}
