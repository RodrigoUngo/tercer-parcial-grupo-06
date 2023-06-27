package com.grupo06.parcial2.models.dto;

import java.util.List;

public class MoviesResponseDTO {
	private List<String> moviesC;
	private List<String> moviesT;
	private List<Integer> moviesD;
	private List<String> categories;
	public MoviesResponseDTO(List<String> moviesC, List<String> moviesT, List<Integer> moviesD, List<String> categories) {
		super();
		this.moviesC = moviesC;
		this.moviesT = moviesT;
		this.moviesD = moviesD;
		this.categories = categories;
	}
	public MoviesResponseDTO() {
		super();
	}
	public List<String> getMoviesC() {
		return moviesC;
	}
	public void setMoviesC(List<String> moviesC) {
		this.moviesC = moviesC;
	}
	public List<String> getMoviesT() {
		return moviesT;
	}
	public void setMoviesT(List<String> moviesT) {
		this.moviesT = moviesT;
	}
	public List<Integer> getMoviesD() {
		return moviesD;
	}
	public void setMoviesD(List<Integer> moviesD) {
		this.moviesD = moviesD;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
