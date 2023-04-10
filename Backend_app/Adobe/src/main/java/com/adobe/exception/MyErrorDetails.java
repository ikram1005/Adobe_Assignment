package com.adobe.exception;

import java.time.LocalDateTime;


public class MyErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	public MyErrorDetails() {
		// TODO Auto-generated constructor stub
	}

	public MyErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
