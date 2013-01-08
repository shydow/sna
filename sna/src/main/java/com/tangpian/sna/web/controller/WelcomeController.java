package com.tangpian.sna.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping(value = "welcome")
	public String printWelcome(ModelMap model) {

		model.put("message", "Hell World");
		return "welcome";

	}
}
