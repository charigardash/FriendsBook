package com.friends.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}
