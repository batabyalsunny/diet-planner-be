package com.mindfire.dietplanner.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * User is an entity class that represents the user and contains the login
 * credentials and role for access checks.
 */
@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; // User ID

	private String name; // Name

	@Column(unique = true)
	private String email; // Email (UNIQUE)

	private String password; // Login Password

	private String role; // Role - ADMIN / USER
	private boolean enabled; // Access enabled or disabled

	@OneToOne(targetEntity = Profile.class)
	private Profile profile; // User's profile data (User - Profile)

	public User() {
		// Default Constructor
	}

	/**
	 * Initializes user with data i.e name, email, password, role and
	 * access flag.
	 * 
	 * @param name
	 *            User's name
	 * @param email
	 *            User's email address
	 * @param password
	 *            Password for login
	 * @param role
	 *            Access role for user
	 * @param enabled
	 *            Access flag i.e enabled/disabled
	 */
	public User(String name, String email, String password, String role, boolean enabled) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}

	// Getter and Setter Methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
