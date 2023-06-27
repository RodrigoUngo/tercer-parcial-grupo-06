package com.grupo06.parcial2.models.dto;

import javax.validation.constraints.NotBlank;

public class BookingShowDTO {
	@NotBlank
	private String username;

	public BookingShowDTO(@NotBlank String username) {
		super();
		this.username = username;
	}

	public BookingShowDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
