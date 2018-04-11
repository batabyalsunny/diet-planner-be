package com.mindfire.dietplanner.core.component;

import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.ResponseSimpleDTO;

/**
 * ResponseSimpleComponent class models a simple response basically used as
 * login service response. The simple response compose of status of the
 * operation and a message from server related to success or error.
 */
@Component
public class ResponseSimpleComponent {

	ResponseSimpleDTO responseSimpleDTO;

	public ResponseSimpleComponent() {
		// Create new simple response
		responseSimpleDTO = new ResponseSimpleDTO();
	}

	/**
	 * Sets the simple response with status and related message.
	 * 
	 * @param status
	 *            Status of the operation
	 * @param message
	 *            Message from server
	 */
	public void setSimpleResponse(String status, String message) {
		responseSimpleDTO.setStatus(status); // Change the status for response
		responseSimpleDTO.setMessage(message); // Change the response message
	}

	/**
	 * Gets simple response object.
	 * 
	 * @return {@link ResponseSimpleDTO} Simple response
	 */
	public ResponseSimpleDTO getSimpleResponse() {
		return responseSimpleDTO; // Return this simple response
	}

	/**
	 * Adds an extra object to the simple response to be pass along with it.
	 * 
	 * @param extra
	 *            Object containing extra data
	 */
	public void addExtra(Object extra) {
		responseSimpleDTO.setExtra(extra);
	}

}
