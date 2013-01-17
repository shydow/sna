package com.tangpian.sna.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tangpian.sna.model.Content;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.service.ContentService;
import com.tangpian.sna.service.ProfileService;

@Controller
public class FetchController {
	@Autowired
	private ContentService contentService;

	@Autowired
	private ProfileService profileService;

	@RequestMapping("/{account}/content/{type}/fetch")
	public String fetchContents(@PathVariable String account,
			@PathVariable int type, ModelMap model) {
		Profile profile = profileService.find(account, type);
		List<Content> contents = contentService.updateContents(profile);
		model.put("contents", contents);
		return "fetch/content";
	}
}
