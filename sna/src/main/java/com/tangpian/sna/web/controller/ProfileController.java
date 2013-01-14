package com.tangpian.sna.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tangpian.sna.component.gplus.GplusProfile;
import com.tangpian.sna.model.Profile;
import com.tangpian.sna.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showForm(@RequestParam String userId, ModelMap model) {
		model.put("userId", userId);
		return "profile/profileForm";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam String account,
			@RequestParam String userId, @RequestParam int type) {
		profileService.save(account, userId, type);
		return "redirect:/user/list";
	}

	@RequestMapping("/list")
	public String list(@RequestParam String userId, ModelMap model) {
		List<Profile> profiles = profileService.find(userId);
		model.put("profiles", profiles);
		return "profile/list";
	}
}
