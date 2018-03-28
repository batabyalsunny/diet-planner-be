package com.mindfire.dietplanner.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.ResponseSimpleComponent;
import com.mindfire.dietplanner.core.component.UserComponent;
import com.mindfire.dietplanner.core.dto.ResponseSimpleDTO;
import com.mindfire.dietplanner.core.dto.UserDTO;

/**
 * LoginService is a service class, used to check user login credentials and
 * return a simple response on success or error.
 */
@Service
public class LoginService {

	private final Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	UserComponent userComponent;

	@Autowired
	ResponseSimpleComponent responseSimpleComponent;

	/**
	 * Gets user login credentials i.e email and password, checks for valid login on
	 * success returns user data else returns error with message.
	 * 
	 * @param email
	 *            User's email
	 * @param password
	 *            Password
	 * @return {@link ResponseSimpleDTO} Simple response DTO
	 */
	public ResponseSimpleDTO checkUserLogin(String email, String password) {
		logger.info("[API] Logging in with " + email);

		UserDTO userDTO = new UserDTO(); // Create new user DTO

		// Search for user in database using email
		try {
			userDTO = userComponent.getUserByEmail(email); // Get user DTO
		} catch (IllegalArgumentException e) {
			logger.error("[API] User with email not found");

			// User not found with given email
			responseSimpleComponent.setSimpleResponse("error", "Email not signed up! Please sign up with email first.");
			responseSimpleComponent.addExtra(null);

			return responseSimpleComponent.getSimpleResponse();
		}

		// Got user with given email, now check if password matches
		// Password match is CASE SENSATIVE
		if (!userDTO.getPassword().equals(password)) {
			logger.error("[API] Login password does not match");

			// Invalid password
			responseSimpleComponent.setSimpleResponse("error", "Password doesn't match!");
			responseSimpleComponent.addExtra(null);
		} else {
			logger.info("[API] Login successful");

			// Password matched
			responseSimpleComponent.setSimpleResponse("success", "Login successful!");
			userDTO.setPassword(null); // Hide password in response
			userDTO.setProfile(null); // Hide user profile in response

			responseSimpleComponent.addExtra(userDTO); // Add user data in extras
		}

		return responseSimpleComponent.getSimpleResponse();
	}

}
