package com.mindfire.dietplanner.core.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.DietPlanDTO;
import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;
import com.mindfire.dietplanner.core.util.DietGenerator;

/**
 * DietPlannerComponent is the class responsible for fetching diet plans for
 * users, based on the user's calorie requirements, diet preferences and list of
 * suggested ideal food items for the diet plan.
 */
@Component
public class DietPlannerComponent {

	@Autowired
	DietPreferenceComponent dietPreferenceComponent;
	@Autowired
	UserComponent userComponent;
	@Autowired
	ProfileComponent profileComponent;
	@Autowired
	IdealFoodItemComponent idealFoodItemComponent;
	@Autowired
	DietGenerator dietGenerator;

	/**
	 * Generates an ideal diet plan based on user health index, diet preferences and
	 * ideal diet items. Diet plan uses prescribed calorie counts for meals and
	 * nutrition ratio.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} Diet plan for user
	 */
	public DietPlanDTO getNewDietPlan(int id) {

		// Get user's profile i.e health index and other calculated data
		ProfileDTO profileDTO = userComponent.getUser(id).getProfile();
		// Get user's diet preferences
		UserDietDTO userDietDTO = dietPreferenceComponent.getUserDiet(id);

		// Using diet generator utility class
		dietGenerator.setUserDietData(profileDTO, userDietDTO);

		// List of food items suggested for each meal course
		List<FoodItemDTO> breakfastItems = idealFoodItemComponent.getIdealFoodItemsForBreakfast();
		List<FoodItemDTO> lunchItems = idealFoodItemComponent.getIdealFoodItemsForLunch();
		List<FoodItemDTO> snacksItems = idealFoodItemComponent.getIdealFoodItemsForSnacks();
		List<FoodItemDTO> dinnerItems = idealFoodItemComponent.getIdealFoodItemsForDinner();

		dietGenerator.setIdealFoodItemsForCourses(breakfastItems, lunchItems, snacksItems, dinnerItems);
		return dietGenerator.generateDietPlan();
	}

	/**
	 * Gets the total amount of calories in the current diet plan.
	 * @return Total amount of calories
	 */
	public int getTotalAmountOfCalories() {
		return dietGenerator.getTotalAmountOfCalories();
	}
}
