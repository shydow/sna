package com.tangpian.sna.component.gplus.fetch;

import java.util.Date;

import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.Person;
import com.tangpian.sna.component.gplus.domain.GplusContent;
import com.tangpian.sna.component.gplus.domain.GplusProfile;
import com.tangpian.sna.model.Content;
import com.tangpian.sna.model.Profile;

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

	public static Content transform(Activity activity,Profile profile) {
		GplusContent content = new GplusContent();
		content.setContentNo(activity.getId());
		content.setNote(activity.getObject().getContent());
		content.setPublished(new Date(activity.getPublished().getValue()));
		content.setUrl(activity.getUrl());
		content.setProfile(profile);
		return content;
	}
	
}
