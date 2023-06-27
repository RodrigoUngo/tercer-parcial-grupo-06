package com.grupo06.parcial2.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo06.parcial2.models.entities.Booking;
import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.models.entities.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser(User user);
	Booking findOneByUserAndSchedule(User user, Schedule schedule);
	
	@Query("SELECT id FROM booking WHERE user = :user")
	List<Long> findIdByUser(@Param("user") User user);
	@Query("SELECT timestamp FROM booking WHERE user = :user")
	List<Timestamp> findTimeByUser(@Param("user") User user);
	@Query("SELECT b.schedule FROM booking b WHERE b.user = :user")
	List<Schedule> findScheduleByUser(@Param("user") User user);
}
