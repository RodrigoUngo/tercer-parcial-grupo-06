package com.grupo06.parcial2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo06.parcial2.models.entities.Category;
import com.grupo06.parcial2.models.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByCategory(Category category);
	@Query("SELECT code FROM movie")
	List<String> findAllCode();
	@Query("SELECT title FROM movie")
	List<String> findAllTitle();
	@Query("SELECT length FROM movie")
	List<Integer> findAllLength();
	@Query("SELECT category FROM movie")
	List<Category> findAllCategory();
	
	@Query("SELECT code FROM movie WHERE category = :category")
	List<String> findCodeByCategory(@Param("category")Category category);
	@Query("SELECT title FROM movie WHERE category = :category")
	List<String> findTitleByCategory(@Param("category")Category category);
	@Query("SELECT length FROM movie WHERE category = :category")
	List<Integer> findLengthByCategory(@Param("category")Category category);
}
