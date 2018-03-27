package com.mindfire.dietplanner.api.service;

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
		UserDTO userDTO = new UserDTO(); // Create new user DTO

		// Search for user in database using email
		try {
			userDTO = userComponent.getUserByEmail(email); // Get user DTO
		} catch (IllegalArgumentException e) {
			// User not found with given email
			responseSimpleComponent.setSimpleResponse("error", "Email not signed up! Please sign up with email first.");
			responseSimpleComponent.addExtra(null);

			return responseSimpleComponent.getSimpleResponse();
		}

		// Got user with given email, now check if password matches
		// Password match is CASE SENSATIVE
		if (!userDTO.getPassword().equals(password)) {
			// Invalid password
			responseSimpleComponent.setSimpleResponse("error", "Password doesn't match!");
			responseSimpleComponent.addExtra(null);
		} else {
			// Password matched
			responseSimpleComponent.setSimpleResponse("success", "Login successful!");
			userDTO.setPassword(null); // Hide password in response
			userDTO.setProfile(null); // Hide user profile in response

			responseSimpleComponent.addExtra(userDTO); // Add user data in extras
		}

		return responseSimpleComponent.getSimpleResponse();
	}

}
