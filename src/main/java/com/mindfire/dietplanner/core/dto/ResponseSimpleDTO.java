package com.mindfire.dietplanner.core.dto;

/**
 * ResponseSimpleDTO is a POJO class used to model a simple server response with
 * a status and message response. If required an extra object data can also be
 * sent with the response.
 */
public class ResponseSimpleDTO {

	String status; // Operation status from server
	String message; // Response message

	Object extra; // Extra data with response

	public ResponseSimpleDTO() {
		// Default constructor
	}

	// Getter and Setter methods
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getExtra() {
		return extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return "ResponseSimpleDTO [status=" + status + ", message=" + message + ", extra=" + extra + "]";
	}

}
