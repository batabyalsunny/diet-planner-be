package com.mindfire.dietplanner.core.dto;

/**
 * UserDTO class is a POJO class used to model user.
 */
public class UserDTO {

	/**
	 * Attributes for user
	 */
	private int id; // User ID

	private String name; // Name
	private String email; // Email
	private String password; // Login Password

	private String role; // Role - ADMIN / USER
	private boolean enabled; // Access enabled or disabled

	private ProfileDTO profileDTO; // User's profile data (User - Profile)

	public UserDTO() {
		// Default Constructor
	}

	/**
	 * Initialize user with data i.e name, email, password, role and
	 * access flag.
	 * 
	 * @param name
	 *            Name of the user
	 * @param email
	 *            Mail address
	 * @param password
	 *            Password
	 * @param role
	 *            Access role for user
	 * @param enabled
	 *            Access enabled/disabled
	 */
	public UserDTO(String name, String email, String password, String role, boolean enabled) {
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

	public ProfileDTO getProfile() {
		return profileDTO;
	}

	public void setProfile(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", profileDTO=" + profileDTO + "]";
	}
	
}
