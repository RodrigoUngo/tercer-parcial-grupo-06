package com.grupo06.parcial2.models.dto;

import java.sql.Timestamp;
import java.util.List;

import com.grupo06.parcial2.models.entities.Schedule;

public class BookingResponseDTO {
	private List<Long> bookingId;
	private List<Timestamp> bookingT;
	private List<String> titles;
	private List<Integer> lengths;
	private List<String> categories;
	public BookingResponseDTO(List<Long> bookingId, List<Timestamp> bookingT, List<String> titles,
			List<Integer> lengths, List<String> categories) {
		super();
		this.bookingId = bookingId;
		this.bookingT = bookingT;
		this.titles = titles;
		this.lengths = lengths;
		this.categories = categories;
	}
	public BookingResponseDTO() {
		super();
	}
	public List<Long> getBookingId() {
		return bookingId;
	}
	public void setBookingId(List<Long> bookingId) {
		this.bookingId = bookingId;
	}
	public List<Timestamp> getBookingT() {
		return bookingT;
	}
	public void setBookingT(List<Timestamp> bookingT) {
		this.bookingT = bookingT;
	}
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<Integer> getLengths() {
		return lengths;
	}
	public void setLengths(List<Integer> lengths) {
		this.lengths = lengths;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
