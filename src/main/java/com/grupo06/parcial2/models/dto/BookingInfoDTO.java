package com.grupo06.parcial2.models.dto;

import javax.validation.constraints.NotBlank;

public class BookingInfoDTO {
	@NotBlank
	private String code;
	public BookingInfoDTO(@NotBlank String code) {
		super();
		this.code = code;
	}
	public BookingInfoDTO() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
