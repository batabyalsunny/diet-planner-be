package com.mindfire.dietplanner.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.component.FoodItemComponent;
import com.mindfire.dietplanner.core.dto.DietPlanDTO;
import com.mindfire.dietplanner.core.dto.DietPlanDetailsDTO;
import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

@Component
public class DietGenerator {

	ProfileDTO profileDTO;
	UserDietDTO userDietDTO;

	// Diet plan for user
	UserDietDTO dietPlan;

	@Autowired
	FoodItemComponent foodItemComponent;

	// List of ideal food items according to courses
	List<FoodItemDTO> breakfastItems;
	List<FoodItemDTO> lunchItems;
	List<FoodItemDTO> snacksItems;
	List<FoodItemDTO> dinnerItems;

	// No. of items as per courses
	int breakfastItemsCount;
	int lunchItemsCount;
	int snacksItemsCount;
	int dinnerItemsCount;

	// Total amount of calories in the diet plan
	int totalCalories = 0;
	// Ideal amount of calories suggested for user
	int calories;

	// Nutritional value limits (weight) in the diet
	int carbs; 							// Limit for Carbohydrates
	int protein; 						// Limit for protein
	int fats; 							// Limit for Fats

	// Total amounts of nutrients in the diet plan
	int totalCarbs = 0;
	int totalProtein = 0;
	int totalFats = 0;
	
	// Diet plan details DTO
	DietPlanDetailsDTO dietPlanDetailsDTO;

	/**
	 * Initializes diet generator with user's profile and diet data. Profile data
	 * contains user's health index and diet data contains user's diet preferences.
	 * 
	 * @param profileDTO
	 *            User's profile data
	 * @param userDietDTO
	 *            User's diet data
	 */
	public void setUserDietData(ProfileDTO profileDTO, UserDietDTO userDietDTO) {
		// User's profile data i.e. BMI, calorie requirements
		this.profileDTO = profileDTO;
		// User's diet preferences
		this.userDietDTO = userDietDTO;

		// Stores the generated diet plan
		dietPlan = new UserDietDTO();
		// Stores nutritional values of the diet plan
		dietPlanDetailsDTO = new DietPlanDetailsDTO();
	}

	/**
	 * Sets list of suggested food items for specific food courses. The list
	 * contains food items considered as ideal for diet planning.
	 * 
	 * @param breakfastItems
	 *            List of food items for breakfast
	 * @param lunchItems
	 *            List of food items for lunch
	 * @param snacksItems
	 *            List of food items for snacks
	 * @param dinnerItems
	 *            List of food items for dinner
	 */
	public void setIdealFoodItemsForCourses(List<FoodItemDTO> breakfastItems, List<FoodItemDTO> lunchItems,
			List<FoodItemDTO> snacksItems, List<FoodItemDTO> dinnerItems) {
		this.breakfastItems = breakfastItems;
		this.lunchItems = lunchItems;
		this.snacksItems = snacksItems;
		this.dinnerItems = dinnerItems;
	}

	/**
	 * Generates diet plan for each food course based on user preferences and user's
	 * type of diet for that course.
	 * 
	 * @return {@link UserDietDTO} Diet plan
	 */
	public DietPlanDTO generateDietPlan() {
		
		// Stores complete diet plan with nutritional details
		DietPlanDTO dietPlanDTO = new DietPlanDTO();
		
		// Update diet item counts for each course
		int diet = profileDTO.getDiet();
		breakfastItemsCount = getDietItemsCount(diet / 100); // No. of items for breakfast
		lunchItemsCount = getDietItemsCount((diet / 10) % 10); // No. of items for lunch
		snacksItemsCount = 2; // No. of items for snacks
		dinnerItemsCount = getDietItemsCount(diet % 10); // No. of items for dinner

		// Adding user preferences to list of food items
		breakfastItems = addUserPreferencesToFoodItemsList(breakfastItems, userDietDTO.getBreakfast());
		lunchItems = addUserPreferencesToFoodItemsList(lunchItems, userDietDTO.getLunch());
		snacksItems = addUserPreferencesToFoodItemsList(snacksItems, userDietDTO.getSnacks());
		dinnerItems = addUserPreferencesToFoodItemsList(dinnerItems, userDietDTO.getDinner());

		// Set the nutritional value limits
		setNutrionalValueLimits();
		
		// Set all the counters to zero before generating courses
		totalCalories = 0;
		totalCarbs = 0;
		totalProtein = 0;
		totalFats = 0;

		// Generate and return diet plan
		generateDietPlanCourses();

		// Set the nutritonal details for this diet plan
		dietPlanDetailsDTO.setIdealCalories(calories);
		dietPlanDetailsDTO.setTotalCaloires(totalCalories);
		
		dietPlanDetailsDTO.setIdealCarbs(carbs);
		dietPlanDetailsDTO.setTotalCarbs(totalCarbs);

		dietPlanDetailsDTO.setIdealProtein(protein);
		dietPlanDetailsDTO.setTotalProtein(totalProtein);
		
		dietPlanDetailsDTO.setIdealFats(fats);
		dietPlanDetailsDTO.setTotalFats(totalFats);
		
		// Add data to diet plan DTO
		dietPlanDTO.setDietPlan(dietPlan); // Actual diet plan
		dietPlanDTO.setDietPlanDetails(dietPlanDetailsDTO); // Diet plan details
		
		return dietPlanDTO;
	}
	
	/**
	 * Generate a random count for meal course items based on user's type of diet
	 * i.e. light, moderate and heavy. Range of food item counts vary from 2 to 3
	 * for light, 3 to 5 for moderate and 5 to 7 for heavy diet.
	 * 
	 * @param diet
	 *            Diet code that defines user's diet for each course
	 * @return Items count based on range of user's diet type
	 */
	private int getDietItemsCount(int diet) {
		Random random = new Random();
		int max; // Max. no of items in diet
		int min; // Min. no of items in diet

		// Light diet type
		if (diet == 1) {
			min = 2;
			max = 3;
		}
		// Moderate diet type
		else if (diet == 2) {
			min = 3;
			max = 5;
		}
		// Heavy diet type
		else {
			min = 5;
			max = 7;
		}
		// Generate a random no. of items from range based on diet type
		return random.nextInt((max + 1) - min) + min;
	}
	
	/**
	 * Performs a union operation on list of user preferences and list of food
	 * items. Returns a list of food items with unique food items from list of food
	 * items and user preferences.
	 * 
	 * @param listOfItems
	 *            List of food items for a specific course
	 * @param listOfPreferences
	 *            List of food items from user preferences
	 * @return List of food items
	 */
	private List<FoodItemDTO> addUserPreferencesToFoodItemsList(List<FoodItemDTO> listOfItems,
			List<FoodItemDTO> listOfPreferences) {
		/*
		 * Generate a union of lists i.e. list of user's preferences and list of all
		 * items for the meal, which may also contain user's preferences. Union
		 * performed on food item IDs which contains all unique items for the meal.
		 */
		Set<Integer> setOfIds = new HashSet<>();
		listOfPreferences.stream().forEach(foodItemDTO -> setOfIds.add(foodItemDTO.getId()));
		listOfItems.stream().forEach(foodItemDTO -> setOfIds.add(foodItemDTO.getId()));

		/*
		 * Generate a sorted new list of food items for the meal course. Diet plan for
		 * the course is generated based on this list. List contains food items suitable
		 * for the course and user preferences.
		 */
		List<FoodItemDTO> newListOfItems = new ArrayList<>();
		// Add the food items to the blank list
		new ArrayList<>(setOfIds).stream().sorted()
				.forEach(id -> newListOfItems.add(foodItemComponent.getFoodItem(id)));

		return newListOfItems;
	}

	/**
	 * Sets nutritional value limits for carbohydrates, proteins and fats for the
	 * user's diet. These values are use to check if a food item can be added to
	 * diet plan. Based on user's body type these values are calculated.
	 */
	private void setNutrionalValueLimits() {
		// Get user's body type
		String bodyType = profileDTO.getBodyType();

		// Get user's daily calorie requirements
		calories = profileDTO.getDailyCalorieCount();

		// Under weight user
		if (bodyType.equals("under")) {
			calories += 250; // Adjust calories for under weight

			carbs = (int) (0.45 * calories); // 45% of calories
			protein = (int) (0.2 * calories); // 20% of calories
			fats = (int) (0.35 * calories);// 35% of calories
		}
		// Normal weight user
		else if (bodyType.equals("normal")) {
			calories += 100; // Adjust calories for normal weight

			carbs = (int) (0.55 * calories); // 55% of calories
			protein = (int) (0.3 * calories); // 30% of calories
			fats = (int) (0.15 * calories);// 15% of calories
		}
		// Over weight user
		else if (bodyType.equals("over")) {
			calories -= 100; // Adjust calories for over weight

			carbs = (int) (0.5 * calories); // 50% of calories
			protein = (int) (0.45 * calories); // 45% of calories
			fats = (int) (0.05 * calories);// 5% of calories
		}
		// Obese user
		else {
			calories -= 150; // Adjust calories for obese

			carbs = (int) (0.65 * calories); // 65% of calories
			protein = (int) (0.35 * calories); // 35% of calories
			fats = 0; // 0% of calories
		}

		// Converting calories to weight (GM)
		carbs = carbs / 4; // 1GM Carbohydrates -> 4 calories
		protein = protein / 4; // 1GM Protein -> 4 calories
		fats = fats / 9; // 1GM Fats -> 9 calories
	}

	/**
	 * Generate different list of food items for all types of course with
	 * approximate calorie count prescribed for that course. Lists are generated
	 * based on ideal calorie count for the course, list of suggested items for the
	 * course and items count based on user's diet type.
	 */
	private void generateDietPlanCourses() {
		int calorieCount; // Calorie count for the meal course
		List<FoodItemDTO> items; // Final list of food items for the meal course

		// Generate and set list of breakfast items
		calorieCount = (int) (calories * 0.2); // 20% of calories
		items = generateDietPlanCourseList(calorieCount, breakfastItems, breakfastItemsCount);
		dietPlan.setBreakfast(items);

		// Generate and set list of lunch items
		calorieCount = (int) (calories * 0.35); // 35% of calories
		items = generateDietPlanCourseList(calorieCount, lunchItems, lunchItemsCount);
		dietPlan.setLunch(items);

		// Generate and set list of snacks items
		calorieCount = (int) (calories * 0.1); // 10% of calories
		items = generateDietPlanCourseList(calorieCount, snacksItems, snacksItemsCount);
		dietPlan.setSnacks(items);

		// Generate and set list of dinner items
		calorieCount = (int) (calories * 0.35); // 35% of calories
		items = generateDietPlanCourseList(calorieCount, dinnerItems, dinnerItemsCount);
		dietPlan.setDinner(items);
	}

	/**
	 * Generates a final list of food items from list of all suggested food items
	 * for a meal course. List size is based on the user's diet type range.
	 * 
	 * @param calorieCount
	 *            Calorie count for the course
	 * @param listOfItems
	 *            List of food items to plan
	 * @param itemsCount
	 *            No. of food items to plan
	 * @return {@link List<FoodItemDTO>} List of planned food items
	 */
	private List<FoodItemDTO> generateDietPlanCourseList(int calorieCount, List<FoodItemDTO> listOfItems,
			int itemsCount) {

		// Actual count for calories
		int calorie = 0;
		// Temp count for calories
		int calorieTemp = 0;

		// Temp count for carbohydrates
		int carbsTemp = 0;
		// Temp count for protein
		int proteinTemp = 0;
		// Temp count for fats
		int fatsTemp = 0;

		// Blank list of final food items for the meal course
		List<FoodItemDTO> finalListOfItems = new ArrayList<>();

		// Index of item to be selected
		int index;
		// For selecting an item with random index from the list
		Random random = new Random();

		// Size of the food items list
		int size = listOfItems.size();

		// Selected food item
		FoodItemDTO foodItem;

		String bodyType = profileDTO.getBodyType();

		// Add the required no. of items to the final list
		for (int i = 0; i < itemsCount; i++) {
			// Get a random food item index
			index = random.nextInt(size);
			// Get the food item with index from the list
			foodItem = listOfItems.get(index);

			// Check if adding the item could exceed the calorie limit
			calorieTemp += foodItem.getNutrients().getCalories();

			// Check if other nutritional values exceed the limit
			carbsTemp += foodItem.getNutrients().getCarbohydrates();
			proteinTemp += foodItem.getNutrients().getProtein();
			fatsTemp += foodItem.getNutrients().getFat();

			// If adding the food item exceeds any of nutritional limits, don't add and
			// continue
			if (calorieTemp > calorieCount || fatsTemp > fats || carbsTemp > carbs || proteinTemp > protein) {

				// Reduce temp calorie count as the item is not added
				calorieTemp -= foodItem.getNutrients().getCalories();
				carbsTemp -= foodItem.getNutrients().getCarbohydrates();
				proteinTemp -= foodItem.getNutrients().getProtein();
				fatsTemp -= foodItem.getNutrients().getFat();

				// Loop back for the same index of diet item
				i--;
				continue;
			}

			// Add the food item to diet plan
			finalListOfItems.add(foodItem); // Add the item to final list
			calorie += foodItem.getNutrients().getCalories();
			listOfItems.remove(index); // Remove from the food list
			size--; // Update the food list size

			totalCarbs += foodItem.getNutrients().getCarbohydrates();
			totalProtein += foodItem.getNutrients().getProtein();
			totalFats += foodItem.getNutrients().getFat();

			// Break if the calorie count is exceeded
			if (calorie >= calorieCount) {
				break;
			}
			// Break if user is obese or over weight and limit for Fats is exceeded
			else if ((bodyType.equals("over") || bodyType.equals("obese")) && totalFats > fats) {
				break;
			}
		}

		totalCalories += calorie;

		// Finally update the weights of food items to match calorie requirements
		return finalListOfItems;
	}

	/**
	 * Get the total amount of calories in the generated diet plan.
	 * 
	 * @return Total calorie count
	 */
	public int getTotalAmountOfCalories() {
		return totalCalories;
	}

}
