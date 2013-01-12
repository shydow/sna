package com.tangpian.sna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangpian.sna.dao.FetchElementDao;
import com.tangpian.sna.model.FetchElement;

@Service
public class AdminService {

	@Autowired
	private FetchElementDao fetchElementDao;
	
	public void addUserFetchElement(FetchElement fetchElement) {
		fetchElementDao.save(new FetchElement(fetchElement.getUserNo(),FetchElement.TYPE_USER,fetchElement.getSource()));
	}

	public List<FetchElement> list() {
		return fetchElementDao.list();
	}
}
