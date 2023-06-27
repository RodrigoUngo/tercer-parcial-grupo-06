package com.grupo06.parcial2.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo06.parcial2.models.entities.Movie;
import com.grupo06.parcial2.models.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("SELECT s FROM schedule s where DATE(timestamp) = :date")
	List<Schedule> findByTimestamp(@Param("date") Date date);
	@Query("SELECT s.id FROM schedule s where DATE(timestamp) = :date")
	List<Long> findIdByTimestamp(@Param("date") Date date);
	@Query("SELECT s.timestamp FROM schedule s where DATE(timestamp) = :date")
	List<Timestamp> findTimeByTimestamp(@Param("date") Date date);
	@Query("SELECT s.price FROM schedule s where DATE(timestamp) = :date")
	List<Float> findPriceByTimestamp(@Param("date") Date date);
	@Query("SELECT s.capacity FROM schedule s where DATE(timestamp) = :date")
	List<Integer> findCapacityByTimestamp(@Param("date") Date date);
	@Query("SELECT s.movie FROM schedule s where DATE(timestamp) = :date")
	List<Movie> findMovieByTimestamp(@Param("date") Date date);
	Schedule findOneById(Long id);
}
