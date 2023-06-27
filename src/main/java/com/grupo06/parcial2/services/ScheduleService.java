package com.grupo06.parcial2.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.grupo06.parcial2.models.entities.Movie;

public interface ScheduleService {
	List<Long> findIdByDate(Date date) throws Exception;
	List<Timestamp> findTimeByDate(Date date) throws Exception;
	List<Float> findPriceByDate(Date date) throws Exception;
	List<Integer> findCapacityByDate(Date date) throws Exception;
	List<Movie> findMovieByDate(Date date) throws Exception;
}
