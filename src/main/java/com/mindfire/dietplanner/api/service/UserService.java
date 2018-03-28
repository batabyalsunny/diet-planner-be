package com.mindfire.dietplanner.api.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.api.util.MailComponent;
import com.mindfire.dietplanner.core.component.ProfileComponent;
import com.mindfire.dietplanner.core.component.UserComponent;
import com.mindfire.dietplanner.core.dto.ProfileDTO;
import com.mindfire.dietplanner.core.dto.ResponseSimpleDTO;
import com.mindfire.dietplanner.core.dto.UserDTO;

/**
 * UserService is a service class, used to add and get user details, update
 * profile and change user's login password.
 */
@Service
public class UserService {

	@Autowired
	UserComponent userComponent;
	@Autowired
	ProfileComponent profileComponent;
	@Autowired
	MailComponent mailComponent;

	// String used to hide email and password
	String hidden = "xxxxx";

	String email; // Store user's email
	int otp; // Store OTP for password reset

	ResponseSimpleDTO responseSimpleDTO;

	public UserService() {
		// Default constructor
	}

	/**
	 * Gets a user's complete data i.e name, role and data.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDTO} User data
	 */
	public UserDTO getUser(int id) {
		UserDTO userDTO = userComponent.getUser(id); // Get user DTO

		userDTO.setEmail(hidden); // Hide the actual email
		userDTO.setPassword(hidden); // Hide the actual password

		return userDTO;
	}

	/**
	 * Saves user's account i.e name, password and role.
	 * 
	 * @param profileDTO
	 * 
	 * @param user
	 *            User instance
	 * @return @return {@link UserDTO} Saved user data
	 */
	public UserDTO setUser(UserDTO userDTO, ProfileDTO profileDTO) {
		// First save new user profile
		profileDTO = profileComponent.setProfile(profileDTO); // Save user profile
		// Add profile to user
		userDTO.setProfile(profileDTO);
		// Now save user data
		userDTO = userComponent.setUser(userDTO); // Get saved user DTO

		return userDTO;
	}

	/**
	 * Updates user profile with height, weight, birth date, activity and diet.
	 * 
	 * @param userId
	 *            User ID
	 * @param profileDTO
	 *            Updated profile
	 * @return {@link UserDTO} User data
	 */
	public UserDTO updateProfile(int userId, ProfileDTO profileDTO) {

		UserDTO newUserDTO = userComponent.getUser(userId); // Get saved user data
		ProfileDTO newProfileDTO = profileComponent.setProfile(profileDTO); // Save new updated profile

		newUserDTO.setProfile(newProfileDTO); // Update user profile to new profile
		newUserDTO = userComponent.setUser(newUserDTO); // Save updated user

		newUserDTO.setEmail(hidden); // Hide the actual email
		newUserDTO.setPassword(hidden); // Hide the actual password

		return newUserDTO;
	}

	/**
	 * Updates user's login password with a new password.
	 * 
	 * @param id
	 *            User ID
	 * @param password
	 *            Current password
	 * @param newPassword
	 *            New password
	 * @return {@link UserDTO} User data
	 */
	public UserDTO changePassword(int id, String password, String newPassword) {
		UserDTO userDTO = userComponent.changePassword(id, password, newPassword); // Get user DTO

		userDTO.setProfile(null);
		userDTO.setEmail(hidden); // Hide the actual email
		userDTO.setPassword(hidden); // Hide the actual password

		return userDTO;
	}

	/**
	 * Sends a generated OTP to the user's email for password reset.
	 * 
	 * @param name
	 *            User's name
	 * @param email
	 *            Email
	 * @return True on success, false on error
	 */
	public ResponseSimpleDTO getForgotPasswordOtp(String name, String email) {

		// Create a new simple response
		responseSimpleDTO = new ResponseSimpleDTO();

		// Generate random 4 digit OTP i.e from 1000 - 9999
		int otp = new Random().nextInt((9999 + 1) - 1000) + 1000;
		
		this.otp = otp; // Update user's OTP
		this.email = email; // Store email for user requesting for password reset

		// Subject and content for the mail with OTP
		String subject = "OTP for Password Reset | Di-Eat!";
		String htmlText = "Hi " + name + " your OTP for account password reset is <b>" + otp
				+ "</b>. <br>Please enter this OTP to reset your account password! <br>Feel free to contact us in case of any issues.";

		// Try to send the OTP via mail
		try {
			mailComponent.sendMailWithHtml(email, subject, htmlText);
		} catch (Exception e) {
			// Error while sending mail
			responseSimpleDTO.setStatus("error");
			responseSimpleDTO.setMessage("Unable to email your OTP, please try again!");
			return responseSimpleDTO;
		}

		// Mail sending success
		responseSimpleDTO.setStatus("success");
		responseSimpleDTO.setMessage("OTP was sent to your email, please check inbox!");
		return responseSimpleDTO;
	}

	public ResponseSimpleDTO resetPassword(int otp, String password) {

		// Create a new simple response
		responseSimpleDTO = new ResponseSimpleDTO();

		// Check for OTP with stored OTP
		if (otp != this.otp) {
			responseSimpleDTO.setStatus("error");
			responseSimpleDTO.setMessage("OTP does not match, please check your email for OTP!");
			return responseSimpleDTO;
		}
		// OTP matched now update password for the user
		else {
			try {
				// Get the user by email ID
				UserDTO userDTO = userComponent.getUserByEmail(this.email);

				int id = userDTO.getId(); // Get user's ID
				String oldPassword = userDTO.getPassword(); // Get user's old password

				changePassword(id, oldPassword, password); // Change old password with new password

				this.otp = 0; // Clear the OTP, used once
				this.email = ""; // Clear the email 

				responseSimpleDTO.setStatus("success");
				responseSimpleDTO.setMessage("Your account password was reset successfully!");
				return responseSimpleDTO;
			} catch (Exception e) {
				// User with email not found
				responseSimpleDTO.setStatus("error");
				responseSimpleDTO.setMessage("Unable to find your account, make sure your email is signed up!");
				return responseSimpleDTO;
			}
		}
	}
}
