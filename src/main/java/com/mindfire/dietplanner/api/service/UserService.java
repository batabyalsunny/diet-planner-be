package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.ProfileComponent;
import com.mindfire.dietplanner.core.component.UserComponent;
import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.dto.UserDTO;

/**
 * UserService implements the business logic for User.
 */
@Service
public class UserService {

	@Autowired
	UserComponent userComponent;

	@Autowired
	ProfileComponent profileComponent;

	// String used to hide email and password
	String hidden = "xxxxx";

	public UserService() {
		// Default constructor
	}

	/**
	 * Gets a user's complete data i.e name, role and data.
	 * 
	 * @param id
	 *            User ID
	 * @return User
	 */
	public UserDTO getUser(int id) {
		UserDTO userDTO = userComponent.getUser(id); // Get user DTO

		userDTO.setEmail(hidden); // Hide the actual email
		userDTO.setPassword(hidden); // Hide the actual password

		return userDTO;
	}

	/**
	 * Saves user's account i.e name, password and role.
	 * 
	 * @param profileDTO
	 * 
	 * @param user
	 *            User instance
	 * @return Saved user instance
	 */
	public UserDTO setUser(UserDTO userDTO, ProfileDTO profileDTO) {
		// First save new user profile
		profileDTO = profileComponent.setProfile(profileDTO); // Save user profile
		// Add profile to user
		userDTO.setProfile(profileDTO);
		// Now save user data
		userDTO = userComponent.setUser(userDTO); // Get saved user DTO

		return userDTO;
	}

	/**
	 * Updates user profile with height, weight, birth date, activity and diet.
	 * 
	 * @param userId
	 *            User ID
	 * @param profileDTO
	 *            DTO of the updated profile
	 * @return
	 */
	public UserDTO updateProfile(int userId, ProfileDTO profileDTO) {

		UserDTO newUserDTO = userComponent.getUser(userId); // Get saved user data
		ProfileDTO newProfileDTO = profileComponent.setProfile(profileDTO); // Save new updated profile

		newUserDTO.setProfile(newProfileDTO); // Update user profile to new profile
		newUserDTO = userComponent.setUser(newUserDTO); // Save updated user

		newUserDTO.setEmail(hidden); // Hide the actual email
		newUserDTO.setPassword(hidden); // Hide the actual password

		return newUserDTO;
	}

}
