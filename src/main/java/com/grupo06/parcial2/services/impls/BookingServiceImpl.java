package com.grupo06.parcial2.services.impls;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.repositories.BookingRepository;
import com.grupo06.parcial2.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepository;

	@Override
	public List<Long> findIdByUser(User user) throws Exception {
		List<Long> foundIds = bookingRepository.findIdByUser(user);
		return foundIds;
	}

	@Override
	public List<Timestamp> findTimeByUser(User user) throws Exception {
		List<Timestamp> foundTimes = bookingRepository.findTimeByUser(user);
		return foundTimes;
	}

	@Override
	public List<Schedule> findScheduleByUser(User user) throws Exception {
		List<Schedule> foundSchedules = bookingRepository.findScheduleByUser(user);
		return foundSchedules;
	}

}
