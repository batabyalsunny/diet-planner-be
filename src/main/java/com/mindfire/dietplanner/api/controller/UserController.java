package com.mindfire.dietplanner.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.UserService;
import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.dto.ResponseSimpleDTO;
import com.mindfire.dietplanner.core.dto.UserDTO;

/**
 * UserController acts as an API end-point for user service. This class
 * implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * Maps GET request to user service, used to get user details.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDTO} User DTO
	 */
	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable int id) {
		// Get user's data from ID
		return userService.getUser(id);
	}

	/**
	 * Maps POST request to user service, used to signup a new user.
	 * 
	 * @param userDTO
	 *            User details
	 * @return {@link UserDTO} User DTO
	 */
	@PostMapping("/signup")
	public UserDTO addUser(@ModelAttribute UserDTO userDTO) {
		// Dummy birth date
		Date date = Date.valueOf("2000-01-01");
		// Dummy user data
		ProfileDTO profileDTO = new ProfileDTO(date, 'M', (short) 0, (short) 0, (short) 0, (short) 0);

		// Save user and dummy profile which is updated later
		return userService.setUser(userDTO, profileDTO);
	}

	/**
	 * Maps POST request to user service, used to setup / update user's profile
	 * data.
	 * 
	 * @param id
	 *            User ID
	 * @param profileDTO
	 *            Profile details
	 * @return {@link UserDTO} User DTO
	 */
	@PostMapping("/profile/{id}")
	public UserDTO addUser(@PathVariable int id, @ModelAttribute ProfileDTO profileDTO) {
		// Save user and profile
		return userService.updateProfile(id, profileDTO);
	}

	/**
	 * Maps POST request to user service, used to update login password.
	 * 
	 * @param id
	 *            User ID
	 * @param password
	 *            Current password
	 * @param newPassword
	 *            New password
	 * @return
	 */
	@PostMapping("/password/{id}")
	public UserDTO changePassword(@PathVariable int id, @RequestParam String password,
			@RequestParam String newPassword) {
		// Return updated user profile
		return userService.changePassword(id, password, newPassword);
	}

	@GetMapping("/forgot-password-otp")
	public ResponseSimpleDTO getForgotPasswordOtp(@RequestParam String name, @RequestParam String email) {
		return userService.getForgotPasswordOtp(name, email);
	}

	@GetMapping("/reset-password")
	public ResponseSimpleDTO resetPassword(@RequestParam int otp, String password) {
		return userService.resetPassword(otp, password);
	}
}
