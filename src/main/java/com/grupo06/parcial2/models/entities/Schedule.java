package com.grupo06.parcial2.models.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "schedule")
public class Schedule {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "timestamp")
	private Timestamp timestamp;
	@Column(name = "price")
	private Float price;
	@Column(name = "capacity")
	private Integer capacity;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_movie", nullable = true)
	private Movie movie;
	public Schedule(Long id, Timestamp timestamp, Float price, Integer capacity, Movie movie) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.price = price;
		this.capacity = capacity;
		this.movie = movie;
	}
	public Schedule() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
