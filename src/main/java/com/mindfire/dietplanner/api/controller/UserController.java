package com.mindfire.dietplanner.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable int id) {
		// Get user's data from ID
		return userService.getUser(id);
	}

	@PostMapping("/signup")
	public UserDTO addUser(@ModelAttribute UserDTO userDTO) {
		// Dummy birth date
		Date date = Date.valueOf("2000-01-01"); 
		// Dummy user data
		ProfileDTO profileDTO = new ProfileDTO(date, 'M', (short)0, (short)0, (short)0, (short)0); 
		
		// Save user and dummy profile which is updated later
		return userService.setUser(userDTO, profileDTO);
	}
	
	@PostMapping("/profile/{id}")
	public UserDTO addUser(@PathVariable int id, @ModelAttribute ProfileDTO profileDTO) {
		// Save user and profile
		return userService.updateProfile(id, profileDTO);
	}

}
