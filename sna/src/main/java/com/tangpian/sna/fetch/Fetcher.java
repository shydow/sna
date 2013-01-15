package com.tangpian.sna.fetch;

import java.util.List;
import java.util.Map;

import com.tangpian.sna.model.Profile;

public interface Fetcher {
	/**
	 * get User information from sns
	 * @param account gplus账户id
	 * @return
	 */
	public Profile fetchProfile(String account);
	
	/**
	 * key为contents的为获取的内容信息
	 * key为relations的为获取的关系信息
	 * @param account gplus帐号id
	 * @return
	 */
	public Map<String, List> fetchContentAndRelation(Profile profile);
}
