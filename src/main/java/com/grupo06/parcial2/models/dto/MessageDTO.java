package com.grupo06.parcial2.models.dto;

public class MessageDTO {
	private String message;

	public MessageDTO(String message) {
		super();
		this.message = message;
	}

	public MessageDTO() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
