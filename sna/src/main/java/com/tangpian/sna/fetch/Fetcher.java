package com.tangpian.sna.fetch;

import java.util.List;

import com.tangpian.sna.model.Content;
import com.tangpian.sna.model.Profile;

public interface Fetcher {
	/**
	 * get User information from sns
	 * @param account gplus账户id
	 * @return
	 */
	public Profile fetchProfile(String account);
	
	public List<Content> fetchContent(String account);
}
