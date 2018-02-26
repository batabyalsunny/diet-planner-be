package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.ProfileComponent;
import com.mindfire.dietplanner.core.dto.ProfileDTO;

/**
 * ProfileService implements the business logic for Profile. It also
 * implements basic CRUD implementations.
 */
@Service
public class ProfileService {

	@Autowired
	ProfileComponent profileComponent;

	public ProfileService() {
		// Default constructor
	}

	/**
	 * Gets a user's data i.e height, weight and diet.
	 * 
	 * @param id
	 *            User ID
	 * @return User's data
	 */
	public ProfileDTO getProfile(int id) {
		return profileComponent.getProfile(id);
	}

	/**
	 * Saves user's data to the database.
	 * 
	 * @param profile
	 *            Instance of Profile
	 * @return Saved user data
	 */
	public ProfileDTO setProfile(ProfileDTO newProfileDTO) {
		return profileComponent.setProfile(newProfileDTO);
	}

}
