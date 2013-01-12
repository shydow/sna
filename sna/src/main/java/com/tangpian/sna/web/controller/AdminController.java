package com.tangpian.sna.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tangpian.sna.model.FetchElement;
import com.tangpian.sna.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/fetchElement/user/add", method = RequestMethod.GET)
	public String showGplusUserForm(ModelMap model) {
		model.put("fetchElement", new FetchElement());
		return "admin/fetchElement/userForm";
	}

	@RequestMapping(value = "/fetchElement/user/add", method = RequestMethod.POST)
	public String addGplusUser(FetchElement fetchElement) {
		adminService.addUserFetchElement(fetchElement);
		return "admin/fetchElement/success";
	}

	@RequestMapping("/fetchElement/user/list")
	public String list(ModelMap model) {
		model.put("fetchElements", adminService.list());
		return "admin/fetchElement/list";
	}
}
