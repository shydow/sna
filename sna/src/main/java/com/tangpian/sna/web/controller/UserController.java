package com.tangpian.sna.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tangpian.sna.model.User;
import com.tangpian.sna.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @RequestMapping(value = "/add", method = RequestMethod.GET)
        public String addUserForm(ModelMap model) {
                model.put("user", new User());
                return "user/userForm";
        }

        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String createUser(User user) {
                userService.createUser(user);
                return "redirect:/user/list";
        }
        
        @RequestMapping("/list")
        public String list(ModelMap model) {
        	List<User> users = userService.listAll();
        	model.put("users", users);
        	return "user/list";
        }
}