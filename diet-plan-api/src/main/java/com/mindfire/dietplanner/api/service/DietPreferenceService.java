package com.mindfire.dietplanner.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.DietPreferenceComponent;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

/**
 * DietPreferenceService is a service class used to get user's diet preferences,
 * add and remove food items to user's diet preferences list.
 */
@Service
public class DietPreferenceService {

	private final Logger logger = LoggerFactory.getLogger(DietPreferenceService.class);
	
	@Autowired
	DietPreferenceComponent dietPreferenceComponent;

	public DietPreferenceService() {
		// Default constructor
	}

	/**
	 * Gets the list of user's diet preferences i.e list of sub-list of food items
	 * according meal courses such as breakfast, lunch and dinner.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} User's diet DTO
	 */
	public UserDietDTO getUserDiet(int id) {
		logger.info("[API] Getting user's diet preferences");
		
		return dietPreferenceComponent.getUserDiet(id);
	}

	/**
	 * Adds a food item to the list of user's preferences for a specific food
	 * course, as desired by the user.
	 * 
	 * @param id
	 *            User ID
	 * @param foodCourse
	 *            Food course
	 * @param foodId
	 *            Food item ID
	 * @return {@link UserDietDTO} User diet DTO
	 */
	public UserDietDTO addItemToUserDiet(int id, int foodCourse, int foodId) {
		logger.info("[API] Adding food item to diet preferences");
		
		return dietPreferenceComponent.addItemToUserDiet(id, foodCourse, foodId);
	}

	/**
	 * Removes a specified food item for a food course, from the list of user's
	 * preferences.
	 * 
	 * @param id
	 *            User ID
	 * @param foodCourse
	 *            Food course
	 * @param foodId
	 *            Food item ID
	 * @return {@link UserDietDTO} User's diet DTO
	 */
	public UserDietDTO removeItemFromUserDiet(int id, int foodCourse, int foodId) {
		logger.info("[API] Removing food item from diet preferences");
		
		return dietPreferenceComponent.removeItemFromUserDiet(id, foodCourse, foodId);
	}
}
