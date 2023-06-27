package com.grupo06.parcial2.services;

import java.sql.Timestamp;
import java.util.List;

import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.models.entities.User;

public interface BookingService {
	List<Long> findIdByUser(User user) throws Exception;
	List<Timestamp> findTimeByUser(User user) throws Exception;
	List<Schedule> findScheduleByUser(User user) throws Exception;
}