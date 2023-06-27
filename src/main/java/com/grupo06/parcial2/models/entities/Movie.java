package com.grupo06.parcial2.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "movie")
public class Movie {
	@Id
	@Column(name = "code")
	private String code;
	@Column(name = "title")
	private String title;
	@Column(name = "length")
	private Integer length;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_category", nullable = true)
	private Category category;
	public Movie(String code, String title, Integer length, Category category) {
		super();
		this.code = code;
		this.title = title;
		this.length = length;
		this.category = category;
	}
	public Movie() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
