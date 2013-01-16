package com.tangpian.sna.component.gplus.fetch;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;
import com.google.api.services.plus.model.Person;
import com.tangpian.sna.fetch.Fetcher;
import com.tangpian.sna.model.Content;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.model.Relation;

@Component
public class GplusFetcher implements Fetcher {

	private static Logger logger = LoggerFactory.getLogger(GplusFetcher.class);
	
	public static final String TYPE_CONTENT = "CONTENTS";
	public static final String TYPE_RELATION = "RELATIONS";

	@Autowired
	private GplusBuilder gplusBuilder;

	public Profile fetchProfile(String account) {
		try {
			Plus plus = gplusBuilder.getServicePlus();
			Person person = plus.people().get(account).execute();
			return GplusUtil.transform(person);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Content> fetchContent(Profile profile) {

		List<Content> contents = new ArrayList<Content>();
		try {
			Plus.Activities.List listActivities = gplusBuilder.getServicePlus()
					.activities().list(profile.getAccount(), "public");
			listActivities.setMaxResults(100L);

			// Execute the request for the first page
			ActivityFeed activityFeed = listActivities.execute();

			// Unwrap the request and extract the pieces we want
			List<Activity> activities = activityFeed.getItems();

			// Loop through until we arrive at an empty page
			while (activities != null) {
				for (Activity activity : activities) {
					contents.add(GplusUtil.transform(activity, profile));
				}

				// We will know we are on the last page when the next page token
				// is
				// null.
				// If this is the case, break.
				if (activityFeed.getNextPageToken() == null) {
					break;
				}

				// Prepare to request the next page of activities
				listActivities.setPageToken(activityFeed.getNextPageToken());

				// Execute and process the next page request
				activityFeed = listActivities.execute();
				activities = activityFeed.getItems();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contents;
	}

	private List<Relation> fetchRelation(List<Content> contents) {
		List<Relation> relations = new ArrayList<Relation>();

		for (Content content : contents) {
			try {
				List<Person> people = gplusBuilder.getServicePlus().people()
						.listByActivity(content.getContentNo(), "plusoners")
						.execute().getItems();
				for (Person person : people) {
					relations.add(new Relation(content.getProfile()
							.getAccount(), person.getId()));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return relations;
	}

	@Override
	public Map<String, List> fetchContentAndRelation(Profile profile) {
		Map<String, List> result = new HashMap<String, List>();
		List<Content> contents = fetchContent(profile);
		result.put(TYPE_CONTENT, contents);
		result.put(TYPE_RELATION, fetchRelation(contents));
		return result;
	}

}
