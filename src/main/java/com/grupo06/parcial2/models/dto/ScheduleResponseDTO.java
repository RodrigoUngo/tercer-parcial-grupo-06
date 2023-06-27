package com.grupo06.parcial2.models.dto;

import java.sql.Timestamp;
import java.util.List;

import com.grupo06.parcial2.models.entities.Movie;

public class ScheduleResponseDTO {
	private List<Long> scheduleId;
	private List<Timestamp> scheduleT;
	private List<Float> scheduleP;
	private List<Integer> scheduleC;
	private List<String> movies;
	private List<Integer> duration;
	public ScheduleResponseDTO(List<Long> scheduleId, List<Timestamp> scheduleT, List<Float> scheduleP,
			List<Integer> scheduleC, List<String> movies, List<Integer> duration) {
		super();
		this.scheduleId = scheduleId;
		this.scheduleT = scheduleT;
		this.scheduleP = scheduleP;
		this.scheduleC = scheduleC;
		this.movies = movies;
		this.duration = duration;
	}
	public ScheduleResponseDTO() {
		super();
	}
	public List<Long> getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(List<Long> scheduleId) {
		this.scheduleId = scheduleId;
	}
	public List<Timestamp> getScheduleT() {
		return scheduleT;
	}
	public void setScheduleT(List<Timestamp> scheduleT) {
		this.scheduleT = scheduleT;
	}
	public List<Float> getScheduleP() {
		return scheduleP;
	}
	public void setScheduleP(List<Float> scheduleP) {
		this.scheduleP = scheduleP;
	}
	public List<Integer> getScheduleC() {
		return scheduleC;
	}
	public void setScheduleC(List<Integer> scheduleC) {
		this.scheduleC = scheduleC;
	}
	public List<String> getMovies() {
		return movies;
	}
	public void setMovies(List<String> movies) {
		this.movies = movies;
	}
	public List<Integer> getDuration() {
		return duration;
	}
	public void setDuration(List<Integer> duration) {
		this.duration = duration;
	}
}
