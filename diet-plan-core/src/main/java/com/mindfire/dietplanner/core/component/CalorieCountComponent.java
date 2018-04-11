package com.mindfire.dietplanner.core.component;

import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.CalorieCountDTO;
import com.mindfire.dietplanner.core.dto.CalorieTrackDTO;
import com.mindfire.dietplanner.core.dto.UserDTO;
import com.mindfire.dietplanner.core.entity.CalorieCount;
import com.mindfire.dietplanner.core.entity.User;
import com.mindfire.dietplanner.core.repository.CalorieCountRepository;

/**
 * CalorieCountComponent class implements get and set methods for user's monthly
 * calorie counts. The calorie counts are used to generate user's calorie track
 * which is the monthly average calorie count according to user's recorded
 * diets.
 */
@Component
public class CalorieCountComponent {

	@Autowired
	CalorieCountRepository calorieCountRepository;

	@Autowired
	UserComponent userComponent;

	ModelMapper mapper;

	public CalorieCountComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Gets calorie counts for the user with user ID.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link CalorieCountDTO} Calorie counts for user
	 */
	public CalorieCountDTO getCalorieCount(int id) {
		CalorieCount calorieCount = calorieCountRepository.findByUserId(id);
		return mapper.map(calorieCount, CalorieCountDTO.class);
	}

	/**
	 * Sets calorie counts for user with user ID.
	 * 
	 * @param id
	 *            User ID
	 * @param dietCounts
	 *            Count of diets recorded by user
	 * @param totalCalories
	 *            Total amount of calories from the diets
	 * @return {@link CalorieCountDTO} Calorie counts for user
	 */
	public CalorieCountDTO setCalorieCount(int id, int[] dietCounts, long[] totalCalories) {
		CalorieCount calorieCount;
		CalorieCountDTO calorieCountDTO;

		// Search for user's calorie counts
		calorieCount = calorieCountRepository.findByUserId(id);
		// If calorie count already exists, set new counts, save and return
		if (calorieCount != null) {
			calorieCount.setDietCounts(dietCounts);
			calorieCount.setTotalCalories(totalCalories);

			calorieCount = calorieCountRepository.save(calorieCount);
			return mapper.map(calorieCount, CalorieCountDTO.class);
		}

		// No calorie count found for user, create new count
		calorieCountDTO = new CalorieCountDTO();

		// Set user to calorie count
		UserDTO userDTO = userComponent.getUser(id);
		User user = mapper.map(userDTO, User.class);
		calorieCountDTO.setUser(user);

		// Set calorie count values
		calorieCountDTO.setDietCounts(dietCounts);
		calorieCountDTO.setTotalCalories(totalCalories);

		// Save and return saved count
		calorieCount = mapper.map(calorieCountDTO, CalorieCount.class);
		calorieCount = calorieCountRepository.save(calorieCount);
		calorieCountDTO = mapper.map(calorieCount, CalorieCountDTO.class);
		return calorieCountDTO;
	}

	/**
	 * Adds calories to user's calorie counts and also increments the count of diet
	 * plans recorded.
	 * 
	 * @param id
	 *            User ID
	 * @param calories
	 *            Calories to be added
	 * @return {@link CalorieCountDTO} Calorie counts for user
	 */
	public CalorieCountDTO addCaloriesToCount(int id, int calories) {

		// Get instance of the current month from calander
		int month = Calendar.getInstance().get(Calendar.MONTH);

		CalorieCountDTO calorieCountDTO;

		// Data for newly added calorie count
		int[] dietCounts = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		long[] totalCalories = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		try {
			// If calorie count exists, get it by user ID
			calorieCountDTO = getCalorieCount(id);
		} catch (Exception e) {
			// If calorie count not found, create new calorie count
			calorieCountDTO = new CalorieCountDTO();

			// Add user to calorie count
			UserDTO userDTO = userComponent.getUser(id);
			User user = mapper.map(userDTO, User.class);
			calorieCountDTO.setUser(user);

			// Set calorie count default values i.e 0
			calorieCountDTO.setDietCounts(dietCounts);
			calorieCountDTO.setTotalCalories(totalCalories);

			// Create the calorie count record
			setCalorieCount(id, dietCounts, totalCalories);
			calorieCountDTO = addCaloriesToCount(id, calories);
			return calorieCountDTO;
		}

		// Get updated calorie counts
		dietCounts = calorieCountDTO.getDietCounts();
		totalCalories = calorieCountDTO.getTotalCalories();

		// Add calories and increment no. of diet counts
		dietCounts[month]++;
		totalCalories[month] += calories;

		// Update the new count, save and return
		calorieCountDTO = setCalorieCount(id, dietCounts, totalCalories);
		return calorieCountDTO;
	}

	/**
	 * Generates calorie track from the user's calorie counts. Calorie track is the
	 * amount of average monthly calorie intakes by the user.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link CalorieTrackDTO} Calorie track for user
	 */
	public CalorieTrackDTO getCalorieTrack(int id) {
		CalorieCountDTO calorieCountDTO;
		// Initial calorie track for new user
		int[] monthlyAverageCalorieCount = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		// Check if user has existing calorie counts, if not set calorie new calorie
		// count
		try {
			calorieCountDTO = getCalorieCount(id);
		} catch (Exception e) {
			// Set new calorie count for new user
			CalorieTrackDTO calorieTrackDTO = new CalorieTrackDTO();
			calorieTrackDTO.setMonthlyAverageCalorieCount(monthlyAverageCalorieCount);
			return calorieTrackDTO;
		}

		// Create new calorie track, to populate from from calorie counts
		CalorieTrackDTO calorieTrack = new CalorieTrackDTO();

		// Get calorie counts and diet counts, to calculate average value
		int[] dietCounts = calorieCountDTO.getDietCounts();
		long[] totalCalories = calorieCountDTO.getTotalCalories();
		int averageValue;

		// Loop through the monthly calorie counts and calculate average for each month
		for (int i = 0; i < 12; i++) {
			// Avoid division by 0, set average to 0
			if (dietCounts[i] == 0) {
				monthlyAverageCalorieCount[i] = 0;
				continue;
			}
			// Calculate average calorie count
			averageValue = (int) (totalCalories[i] / dietCounts[i]);
			monthlyAverageCalorieCount[i] = averageValue;
		}

		// Set complete array of values to calorie track and return
		calorieTrack.setMonthlyAverageCalorieCount(monthlyAverageCalorieCount);
		return calorieTrack;
	}
}
