package com.tangpian.sna.fetch.gplus;

import com.google.api.services.plus.model.Person;
import com.tangpian.sna.model.User;

public class GplusUtil {
	public static User transform(Person person) {
		User user = new User();
		
		user.setBiography(person.getAboutMe());
		user.setGender(person.getGender());
		user.setImageUrl(person.getImage().getUrl());
		user.setNickname(person.getNickname());
		user.setRelationshipStatus(person.getRelationshipStatus());
		user.setUrl(person.getUrl());
		user.setUsername(person.getDisplayName());
		user.setUserNo(person.getId());
		
		return user;
	}
}
