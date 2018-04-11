package com.mindfire.dietplanner.core.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.entity.Profile;
import com.mindfire.dietplanner.core.repository.ProfileRepository;

/**
 * ProfileComponent class implements get and set methods for user's profile i.e
 * data containing user's height, weight, activity and other data.
 */
@Component
public class ProfileComponent {

	@Autowired
	ProfileRepository profileRepository;

	ModelMapper mapper;
	ProfileDTO profileDTO;

	public ProfileComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Gets a user's data i.e height, weight and diet.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link ProfileDTO} User's profile data
	 */
	public ProfileDTO getProfile(int id) {
		Profile profile = profileRepository.findOne(id); // Get entity
		profileDTO = mapper.map(profile, ProfileDTO.class); // Map to DTO

		return profileDTO;
	}

	/**
	 * Saves user's data to the database.
	 * 
	 * @param profile
	 *            Profile data
	 * @return {@link ProfileDTO} Saved user's profile data
	 */
	public ProfileDTO setProfile(ProfileDTO newProfileDTO) {
		Profile newProfile = mapper.map(newProfileDTO, Profile.class); // Map DTO to entity
		newProfile = profileRepository.save(newProfile); // Save entity

		profileDTO = mapper.map(newProfile, ProfileDTO.class); // Map entity back to DTO

		return profileDTO;
	}

}
