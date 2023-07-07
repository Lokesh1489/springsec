package com.ak.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedBusUserOperationController {

	@GetMapping("/home")
	public String showHomePage() {
		return "<h1> Welcome to RedBus.com Home Page </h1>";
	}

	@GetMapping("/after")
	public String afterLoginPage() {
		return "<h1> Page after login activity </h1>";
	}

	@GetMapping("/user")
	public Authentication showUserDetails(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
}
