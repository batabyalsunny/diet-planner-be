package com.mindfire.dietplanner.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.api.util.DietPlanMailTemplate;
import com.mindfire.dietplanner.api.util.MailComponent;
import com.mindfire.dietplanner.core.component.DietPlannerComponent;
import com.mindfire.dietplanner.core.component.UserComponent;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

@Service
public class DietPlannerService {

	@Autowired
	DietPlannerComponent dietPlannerComponent;

	@Autowired
	UserComponent userComponent;

	@Autowired
	MailComponent mailComponent;

	@Autowired
	DietPlanMailTemplate dietPlanMailTemplate;

	// Stores user's current diet plan
	UserDietDTO userDietPlan;

	public UserDietDTO getNewDietPlan(int id) {
		userDietPlan = dietPlannerComponent.getNewDietPlan(id, 1);
		return userDietPlan;
	}

	public UserDietDTO getCurrentDietPlan(int id) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd - HH.mm.ss").format(new Date());
		String mailTo = userComponent.getUser(id).getEmail();
		String subject = "Diet Plan " + timeStamp + " | Di-Eat!";
		String htmlText = dietPlanMailTemplate.getDietPlanHtml(userDietPlan);
		
		mailComponent.sendMailWithHtml(mailTo, subject, htmlText);
		
		return userDietPlan;
	}
}
