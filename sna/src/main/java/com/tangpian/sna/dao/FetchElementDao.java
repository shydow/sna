package com.tangpian.sna.dao;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tangpian.sna.model.FetchElement;

@Repository
public class FetchElementDao {

	@Autowired
	private PersistenceManagerFactory persistenceManagerFactory;

	public void save(FetchElement... fetchElements) {
		persistenceManagerFactory.getPersistenceManager().makePersistentAll(
				fetchElements);
	}

	public void save(List<FetchElement> fetchElements) {
		persistenceManagerFactory.getPersistenceManager().makePersistentAll(
				fetchElements);
	}

	public void updateFetchTime(String id) {
		FetchElement fetchElement = (FetchElement) persistenceManagerFactory
				.getPersistenceManager().getObjectById(id);
		fetchElement.setFetchTime(new Date());
		persistenceManagerFactory.getPersistenceManager().makePersistent(fetchElement);
	}

	public List<FetchElement> list() {
		Query q = persistenceManagerFactory.getPersistenceManager().newQuery(
				FetchElement.class);
		return (List<FetchElement>) q.execute();
	}

	public List<FetchElement> listByTypeAndSource(int type, int source) {
		Query q = persistenceManagerFactory.getPersistenceManager().newQuery(
				FetchElement.class, "type == :type && source == :source");
		return (List<FetchElement>) q.execute(type, source);
	}
}
