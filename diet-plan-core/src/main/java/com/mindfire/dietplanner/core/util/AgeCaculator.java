package com.mindfire.dietplanner.core.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 * Utility class to calculate the User's age from birth date.
 */
public class AgeCaculator {

	/**
	 * Calculates the user's age from the saved birth date.
	 * 
	 * @param dob
	 *            Birth date
	 * @return User's age
	 */
	public int getAge(Date dob) {
		// Get current local date
		LocalDate currentDate = LocalDate.now();
		LocalDate birthDate = dob.toLocalDate();

		// If years not null calculate and return years i.e age
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

}
