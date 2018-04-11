package com.mindfire.dietplanner.core.component;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;
import com.mindfire.dietplanner.core.entity.DietPreference;
import com.mindfire.dietplanner.core.entity.FoodItem;
import com.mindfire.dietplanner.core.entity.User;
import com.mindfire.dietplanner.core.repository.DietPreferenceRepository;
import com.mindfire.dietplanner.core.repository.FoodItemRepository;
import com.mindfire.dietplanner.core.repository.UserRepository;

/**
 * DietPreferenceComponent implements get and set methods for the list of food
 * items from user's diet preferences sorted according to various meal courses
 * such as breakfast, lunch, snacks and dinner.
 */
@Component
public class DietPreferenceComponent {

	@Autowired
	DietPreferenceRepository dietPreferenceRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	FoodItemRepository foodItemRepository;

	ModelMapper mapper;
	UserDietDTO userDietDTO;

	public DietPreferenceComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Returns user diet preferences as an array of all types of meal courses.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} User diet preferences
	 */
	public UserDietDTO getUserDiet(int id) {
		
		// Create new blank Diet DTO to populate
		userDietDTO = new UserDietDTO();
		
		// Get list of user's preferred foods and their courses
		List<DietPreference> dietPreferences = dietPreferenceRepository.findByUserId(id);

		// Loop through list to extract food items list and separate foods in categories
		for (int i = 0; i < dietPreferences.size(); i++) {

			// Get the preferred diet choice
			DietPreference dietPreference = dietPreferences.get(i);

			// Course of the food item as user's preference
			int foodCourse = dietPreference.getCourse();

			FoodItem foodItem = dietPreference.getFoodItem(); // Get entity
			FoodItemDTO foodItemDTO = mapper.map(foodItem, FoodItemDTO.class); // Map to DTO

			// Separate food items according to food courses i.e breakfast to dinner
			if (foodCourse == 1) {
				userDietDTO.addBreakfastItem(foodItemDTO); // Breakfast items
			} else if (foodCourse == 2) {
				userDietDTO.addLunchItem(foodItemDTO); // Lunch items
			} else if (foodCourse == 3) {
				userDietDTO.addSnacksItem(foodItemDTO); // Snacks items
			} else if (foodCourse == 4) {
				userDietDTO.addDinnerItem(foodItemDTO); // Dinner items
			} else if (foodCourse == 5) {
				userDietDTO.addExtraItem(foodItemDTO); // Extra items
			}

		}
		return userDietDTO;
	}

	/**
	 * Adds a required food item to user's diet preferences according to a specific
	 * meal course. Users can add various food items to specific courses such as
	 * breakfast, lunch, dinner.
	 * 
	 * @param id
	 *            User ID
	 * @param foodCourse
	 *            Food course ID
	 * @param foodId
	 *            Food item ID
	 * @return {@link UserDietDTO} User diet preferences
	 */
	public UserDietDTO addItemToUserDiet(int id, int foodCourse, int foodId) {
		// Get requested user by ID
		User user = userRepository.findOne(id);
		// Get requested food item by ID
		FoodItem foodItem = foodItemRepository.findOne(foodId);

		// Get requested diet preference if already exists else returns null
		DietPreference dietPreference = dietPreferenceRepository.findByUserIdAndFoodItemIdAndCourse(id, foodId,
				foodCourse);

		// If diet preference already exists, no need to save just return diet
		if (dietPreference != null) {
			userDietDTO = getUserDiet(id); // Get user's diet preferences
			return userDietDTO;
		}

		// Create new diet preference and add requested user, food and course
		dietPreference = new DietPreference();
		dietPreference.setCourse(foodCourse);
		dietPreference.setUser(user);
		dietPreference.setFoodItem(foodItem);

		// Save the new diet preference
		dietPreferenceRepository.save(dietPreference);

		// Get updated user diet and return
		userDietDTO = getUserDiet(id);
		return userDietDTO;
	}

	/**
	 * Removes food item from user's diet preferences list with respect to a
	 * specific meal course.
	 * 
	 * @param id
	 *            User ID
	 * @param foodCourse
	 *            Food course
	 * @param foodId
	 *            Food item ID
	 * @return {@link UserDietDTO} User diet preferences
	 */
	public UserDietDTO removeItemFromUserDiet(int id, int foodCourse, int foodId) {
		// Get requested diet preference to delete
		DietPreference dietPreference = dietPreferenceRepository.findByUserIdAndFoodItemIdAndCourse(id, foodId,
				foodCourse);
		// Delete the diet preference for user
		dietPreferenceRepository.delete(dietPreference);

		// Get updated user diet and return
		userDietDTO = getUserDiet(id);
		return userDietDTO;
	}

}
