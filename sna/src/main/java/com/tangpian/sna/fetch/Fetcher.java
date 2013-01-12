package com.tangpian.sna.fetch;

import java.util.List;

import com.tangpian.sna.model.User;

public interface Fetcher {
	/**
	 * get User information from sns
	 * @param id
	 * @return
	 */
	public List<User> fetch(String... id);
	
	
	
	
}
