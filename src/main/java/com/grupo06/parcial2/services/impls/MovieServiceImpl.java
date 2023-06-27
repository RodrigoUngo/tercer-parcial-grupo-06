package com.grupo06.parcial2.services.impls;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo06.parcial2.models.entities.Booking;
import com.grupo06.parcial2.models.entities.Category;
import com.grupo06.parcial2.models.entities.Movie;
import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.repositories.BookingRepository;
import com.grupo06.parcial2.repositories.CategoryRepository;
import com.grupo06.parcial2.repositories.MovieRepository;
import com.grupo06.parcial2.repositories.ScheduleRepository;
import com.grupo06.parcial2.repositories.UserRepository;
import com.grupo06.parcial2.services.MovieServices;

@Service
public class MovieServiceImpl implements MovieServices {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public List<Movie> findAll() throws Exception {
		return movieRepository.findAll();
	}
	
	@Override
	public List<Schedule> findByDate(Date date) throws Exception {
		List<Schedule> foundSchedules = scheduleRepository	
				.findByTimestamp(date);
		
		return foundSchedules;
	}
	
	@Override
	public List<Movie> findByCategory(String categoryId) throws Exception {
		Category category = categoryRepository.findOneByCode(categoryId);
		
		List<Movie> foundMovies = movieRepository	
				.findByCategory(category);
		
		return foundMovies;
	}
	
	@Override
	public void reservate(Timestamp time, String username, Long code) throws Exception{
		Booking booking = new Booking();
		User user = userRepository.findOneByUsername(username);
		Schedule schedule = scheduleRepository.findOneById(code);
		
		booking.setSchedule(schedule);
		booking.setTimestamp(time);
		booking.setUser(user);
		
		bookingRepository.save(booking);
	}
	
	@Override
	public List<Booking> showReservations(String username) throws Exception{
		User user = userRepository.findOneByUsername(username);
		List<Booking> foundBooking = bookingRepository.findByUser(user);
		return foundBooking;
	}
	
	@Override
	public void deleteReservation(String username, Long code) throws Exception{
		User user = userRepository.findOneByUsername(username);
		Schedule schedule = scheduleRepository.findOneById(code);
		Booking booking = bookingRepository.findOneByUserAndSchedule(user, schedule);
		bookingRepository.delete(booking);
	}

	@Override
	public List<String> findCodes() throws Exception {
		List<String> foundCodes = movieRepository.findAllCode();
		return foundCodes;
	}

	@Override
	public List<String> findTitles() throws Exception {
		List<String> foundTitles = movieRepository.findAllTitle();
		return foundTitles;
	}

	@Override
	public List<Integer> findDuration() throws Exception {
		List<Integer> foundLength = movieRepository.findAllLength();
		return foundLength;
	}

	@Override
	public List<Category> findCategories() throws Exception {
		List<Category> foundCategories = movieRepository.findAllCategory();
		return foundCategories;
	}

	@Override
	public List<String> findCodesByCategory(String categoryId) throws Exception {
		Category category = categoryRepository.findOneByCode(categoryId);
		
		List<String> foundCodes = movieRepository.findCodeByCategory(category);
		return foundCodes;
	}

	@Override
	public List<String> findTitlesByCategory(String categoryId) throws Exception {
		Category category = categoryRepository.findOneByCode(categoryId);
		
		List<String> foundTitles = movieRepository.findTitleByCategory(category);
		return foundTitles;
	}

	@Override
	public List<Integer> findDurationByCategory(String categoryId) throws Exception {
		Category category = categoryRepository.findOneByCode(categoryId);
		
		List<Integer> foundLength = movieRepository.findLengthByCategory(category);
		return foundLength;
	}

	@Override
	public Category getCategory(String category) throws Exception {
		return categoryRepository.findOneByCode(category);
	}
}
