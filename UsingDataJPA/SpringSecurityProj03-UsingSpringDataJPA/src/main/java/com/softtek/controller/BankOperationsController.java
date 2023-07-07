package com.softtek.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank")
public class BankOperationsController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}

	@GetMapping("/balance")
	public String showBalance(Map<String, Object> map) {
		map.put("account_balance", new Random().nextInt(100000));
		return "show_balance";
	}

	@GetMapping("/offers")
	public String showoffer() {
		return "show_offer";
	}

	@GetMapping("/loanApprove")
	public String approveBalance(Map<String, Object> map) {
		map.put("loan_amount", new Random().nextInt(100000));
		return "loan_approve";
	}
	@GetMapping("/denied")
	public String accessDenied() {
		return "authorization_failed";
	}
}
