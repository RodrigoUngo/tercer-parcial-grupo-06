package com.grupo06.parcial2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo06.parcial2.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	Category findOneByCode(String code);
}
