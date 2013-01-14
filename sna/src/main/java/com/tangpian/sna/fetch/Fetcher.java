package com.tangpian.sna.fetch;

import java.util.List;

import com.tangpian.sna.model.Profile;

public interface Fetcher {
	/**
	 * get User information from sns
	 * @param id
	 * @return
	 */
	public List<Profile> fetchProfile(String... id);
	
}
