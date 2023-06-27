package com.grupo06.parcial2.services.impls;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo06.parcial2.models.entities.Movie;
import com.grupo06.parcial2.repositories.ScheduleRepository;
import com.grupo06.parcial2.services.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public List<Long> findIdByDate(Date date) throws Exception{
		List<Long> foundIds = scheduleRepository.findIdByTimestamp(date);
		return foundIds;
	}
	
	@Override
	public List<Timestamp> findTimeByDate(Date date) throws Exception {
		List<Timestamp> foundTimes = scheduleRepository	
				.findTimeByTimestamp(date);
		
		return foundTimes;
	}

	@Override
	public List<Float> findPriceByDate(Date date) throws Exception {
		List<Float> foundPrices = scheduleRepository	
				.findPriceByTimestamp(date);
		
		return foundPrices;
	}

	@Override
	public List<Integer> findCapacityByDate(Date date) throws Exception {
		List<Integer> foundCapacity = scheduleRepository	
				.findCapacityByTimestamp(date);
		
		return foundCapacity;
	}
	@Override
	public List<Movie> findMovieByDate(Date date) throws Exception {
		List<Movie> foundMovies = scheduleRepository	
				.findMovieByTimestamp(date);
		
		return foundMovies;
	}
}
