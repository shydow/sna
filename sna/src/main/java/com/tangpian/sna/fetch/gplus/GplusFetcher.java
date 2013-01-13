package com.tangpian.sna.fetch.gplus;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.tangpian.sna.fetch.Fetcher;
import com.tangpian.sna.model.GplusProfile;
import com.tangpian.sna.model.Profile;

@Component
public class GplusFetcher implements Fetcher {

	@Autowired
	private GplusBuilder gplusBuilder;

	@Override
	public List<Profile> fetchProfile(String... ids) {
		List<Profile> users = new ArrayList<Profile>();
		try {
			Plus plus = gplusBuilder.getServicePlus();
			for (String id : ids) {
				Person person = plus.people().get(id).execute();
				users.add(GplusUtil.transform(person));
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
