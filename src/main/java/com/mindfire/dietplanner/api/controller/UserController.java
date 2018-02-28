package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.UserService;
import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.dto.UserDTO;

/**
 * UserController acts as an API end-point for User data. Class implements
 * mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable int id) {
		// Get user's data from ID
		return userService.getUser(id);
	}

	@PostMapping("")
	public UserDTO addUser(@ModelAttribute UserDTO userDTO, @ModelAttribute ProfileDTO profileDTO) {
		// Save user and profile
		return userService.setUser(userDTO, profileDTO);
	}
}
