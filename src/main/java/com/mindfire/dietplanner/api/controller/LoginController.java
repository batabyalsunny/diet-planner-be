package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.LoginService;
import com.mindfire.dietplanner.core.dto.ResponseSimpleDTO;

/**
 * LoginController acts as an API end-point for user login. This class
 * implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * Maps GET request for login service, used to check user's login credentials
	 * and if found correct / incorrect returns a simple success / error response.
	 * 
	 * @param email
	 *            User's email
	 * @param password
	 *            Login password
	 * @return {@link ResponseSimpleDTO} Simple response DTO
	 */
	@GetMapping("")
	public ResponseSimpleDTO checkUserLogin(@RequestParam String email, @RequestParam String password) {
		return loginService.checkUserLogin(email, password);
	}

}
