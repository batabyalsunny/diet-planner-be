package com.mindfire.dietplanner.core.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.UserDTO;
import com.mindfire.dietplanner.core.entity.User;
import com.mindfire.dietplanner.core.repository.UserRepository;

/**
 * UserComponent implements get and set methods for user entity, it is used to
 * create new user, get existing user details, check user login and update
 * user's login password.
 */
@Component
public class UserComponent {

	@Autowired
	UserRepository userRepository;

	ModelMapper mapper;
	UserDTO userDTO;

	public UserComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Gets a user's complete data i.e name, role and data.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link User} User
	 */
	public UserDTO getUser(int id) {
		User user = userRepository.findOne(id); // Get entity
		userDTO = mapper.map(user, UserDTO.class); // Map to DTO

		return userDTO;
	}

	/**
	 * Saves user's complete data i.e name, role and data.
	 * 
	 * @param user
	 *            User data
	 * @return {@link UserDTO} Saved user data
	 */
	public UserDTO setUser(UserDTO newUserDTO) {
		User newUser = mapper.map(newUserDTO, User.class); // Map DTO to entity
		newUser = userRepository.save(newUser); // Save entity
		userDTO = mapper.map(newUser, UserDTO.class); // Map entity back to DTO

		return userDTO;
	}

	/**
	 * Gets user data finding by user's email used for login.
	 * 
	 * @param email
	 *            User's email
	 * @return {@link User} User data
	 */
	public UserDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email); // Get entity
		userDTO = mapper.map(user, UserDTO.class); // Map to DTO

		return userDTO;
	}

	/**
	 * Gets user's current password and new password. If found correct current
	 * password is updated with the new password.
	 * 
	 * @param id
	 *            User ID
	 * @param password
	 *            Password
	 * @param newPassword
	 *            New password
	 * @return {@link UserDTO} User data
	 */
	public UserDTO changePassword(int id, String password, String newPassword) {
		User user = userRepository.findOne(id);

		if (user.getPassword().equals(password)) {
			user.setPassword(newPassword);
			user = userRepository.save(user);

			return mapper.map(user, UserDTO.class);
		} else {
			return null;
		}
	}
}
