package com.tangpian.sna.fetch.gplus;

import com.google.api.services.plus.model.Person;
import com.tangpian.sna.model.GplusProfile;

public class GplusUtil {
	public static GplusProfile transform(Person person) {
		GplusProfile user = new GplusProfile();
		
		user.setBiography(person.getAboutMe());
		user.setGender(person.getGender());
		user.setImageUrl(person.getImage().getUrl());
		user.setNickname(person.getNickname());
		user.setRelationshipStatus(person.getRelationshipStatus());
		user.setUrl(person.getUrl());
		user.setUsername(person.getDisplayName());
		user.setAccount(person.getId());
		
		return user;
	}
}
