package com.mindfire.dietplanner.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.api.util.DietPlanMailTemplate;
import com.mindfire.dietplanner.api.util.MailComponent;
import com.mindfire.dietplanner.core.component.CalorieCountComponent;
import com.mindfire.dietplanner.core.component.DietPlannerComponent;
import com.mindfire.dietplanner.core.component.UserComponent;
import com.mindfire.dietplanner.core.dto.CalorieCountDTO;
import com.mindfire.dietplanner.core.dto.CalorieTrackDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

/**
 * DietPlannerService is a service class used generate new diet plans for user,
 * get user's current diet plan, record user's calorie intakes and generate a
 * track record for user's monthly calorie intakes.
 */
@Service
public class DietPlannerService {

	private final Logger logger = LoggerFactory.getLogger(DietPlannerService.class);

	@Autowired
	DietPlannerComponent dietPlannerComponent;

	@Autowired
	UserComponent userComponent;

	@Autowired
	MailComponent mailComponent;

	@Autowired
	CalorieCountComponent calorieCountComponent;

	@Autowired
	DietPlanMailTemplate dietPlanMailTemplate;

	// Stores user's current diet plan
	UserDietDTO userDietPlan;

	/**
	 * Gets a new generated diet plan for the user according to his diet preferences
	 * and suggested diet food items.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} Diet plan
	 */
	public UserDietDTO getNewDietPlan(int id) {
		logger.info("[API] Getting new diet plan for user ID: " + id);

		// Generate and return a new diet plan for user
		userDietPlan = dietPlannerComponent.getNewDietPlan(id);
		return userDietPlan;
	}

	/**
	 * Gets the current diet plan suggested to the user. The diet plan is then
	 * mailed to the user's email.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} Diet plan
	 */
	public UserDietDTO getCurrentDietPlan(int id) {
		logger.info("[API] Sending diet plan to user via email");

		// Generate time stamp for the diet plan
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd - HH.mm.ss").format(new Date());

		// Get user's email, add subject to mail and diet plan as HTML
		String mailTo = userComponent.getUser(id).getEmail();
		String subject = "Diet Plan " + timeStamp + " | Di-Eat!";
		String htmlText = dietPlanMailTemplate.getDietPlanHtml(userDietPlan);

		// Send the diet to user's mail
		mailComponent.sendMailWithHtml(mailTo, subject, htmlText);
		return userDietPlan;
	}

	/**
	 * Records the current diet plan's calorie values to user's monthly calorie
	 * counter.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link CalorieCountDTO} User's calorie counts
	 */
	public CalorieCountDTO recordDietPlan(int id) {
		logger.info("[API] Recording the diet plan for user ID: " + id);

		// Record the amount of calories from user's diet plan
		int calorieCountInCurrentDietPlan = dietPlannerComponent.getTotalAmountOfCalories();
		return calorieCountComponent.addCaloriesToCount(id, calorieCountInCurrentDietPlan);
	}

	/**
	 * Generates user's calorie track record i.e his monthly average calorie intakes
	 * from his recorded diets.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link CalorieTrackDTO} User's calorie track record
	 */
	public CalorieTrackDTO trackCalorieCount(int id) {
		logger.info("[API] Getting calorie track for user ID: " + id);

		// Return user's calorie track record
		return calorieCountComponent.getCalorieTrack(id);
	}
}
