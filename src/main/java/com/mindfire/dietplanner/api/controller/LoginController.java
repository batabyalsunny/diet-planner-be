package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.LoginService;
import com.mindfire.dietplanner.core.dto.ResponseSimpleDTO;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("")
	public ResponseSimpleDTO checkUserLogin(@RequestParam String email, @RequestParam String password) {
		return loginService.checkUserLogin(email, password);
	}
	
}
