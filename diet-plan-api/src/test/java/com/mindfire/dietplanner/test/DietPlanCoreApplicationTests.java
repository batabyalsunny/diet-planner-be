package com.mindfire.dietplanner.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindfire.dietplanner.core.component.ProfileComponent;
import com.mindfire.dietplanner.core.dto.ProfileDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DietPlanCoreApplicationTests {

	@Autowired
	ProfileComponent profileComponent;

	// User profile
	ProfileDTO profile;

	// User ID to be tested
	final int userId = 1;

	// User details to be tested
	final int actualBmi = 17;
	final int actualAge = 25;
	final int actualCalories = 2076;
	

	@Test
	public void getBmiTest() {
		// Test the BMI value for user
		profile = profileComponent.getProfile(userId);
		int bmi = (int) profile.getBmi();
		assertThat(bmi).isEqualTo(actualBmi);
	}

	@Test
	public void getAgeTest() {
		// Test the age for user
		profile = profileComponent.getProfile(userId);
		int age = profile.getAge();
		assertThat(age).isEqualByComparingTo(actualAge);
	}

	@Test
	public void getDailyCalorieCountTest() {
		// Test the calorie count for user
		profile = profileComponent.getProfile(userId);
		int calories = profile.getDailyCalorieCount();
		assertThat(calories).isEqualByComparingTo(actualCalories);
	}

}
