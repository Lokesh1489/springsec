package com.softtek.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtek.entity.UserInfo;
import com.softtek.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping("/register") // for launching the form
	public String showUserRegistionPage(@ModelAttribute("userInfo") UserInfo details) {
		return "user_register";
	}

	@PostMapping("/register")
	public String registerUser(Map<String, Object> map, @ModelAttribute("userInfo") UserInfo details) {
		// use Service
		String msg = service.register(details);

		map.put("message", msg);

		// return LVN
		return "user_registered_success";
	}
	@GetMapping("/showLogin")
	public String showLoginpage() {
		return "Custom_login";
	}
}
