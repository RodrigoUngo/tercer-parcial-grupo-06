package com.grupo06.parcial2.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "category")
public class Category {
	@Id
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	
	public Category(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public Category() {
		super();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
