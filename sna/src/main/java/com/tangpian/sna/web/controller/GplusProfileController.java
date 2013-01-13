package com.tangpian.sna.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tangpian.sna.model.GplusProfile;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.service.ProfileService;

@Controller
@RequestMapping("/profile/gplus")
public class GplusProfileController {

	@Autowired
	private ProfileService profileService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showForm(@RequestParam String userId, ModelMap model) {
		model.put("userId", userId);
		return "profile/gplusForm";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam String account, @RequestParam String userId) {
		profileService.save(account, userId, Profile.TYPE_GPLUS);
		return "redirect:/user/list";
	}

	@RequestMapping("/list")
	public String list(@RequestParam String userId,ModelMap model) {
		GplusProfile profile = (GplusProfile) profileService.find(userId, Profile.TYPE_GPLUS);
		model.put("profile", profile);
		return "profile/gplusProfile";
	}
}
