package com.grupo06.parcial2.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo06.parcial2.models.dto.BookingInfoDTO;
import com.grupo06.parcial2.models.dto.BookingResponseDTO;
import com.grupo06.parcial2.models.dto.BookingShowDTO;
import com.grupo06.parcial2.models.dto.MessageDTO;
import com.grupo06.parcial2.models.dto.MoviesResponseDTO;
import com.grupo06.parcial2.models.dto.ScheduleResponseDTO;
import com.grupo06.parcial2.models.entities.Booking;
import com.grupo06.parcial2.models.entities.Category;
import com.grupo06.parcial2.models.entities.Movie;
import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.services.BookingService;
import com.grupo06.parcial2.services.MovieServices;
import com.grupo06.parcial2.services.ScheduleService;
import com.grupo06.parcial2.services.UserService;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
	
	@Autowired
	MovieServices movieServices;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/movies")
	public String findAllMovies(Model model){
		try {
			List<String> moviesC = movieServices.findCodes();
			List<String> moviesT = movieServices.findTitles();
			List<Integer> moviesD = movieServices.findDuration();
			List<Category> moviesCa = movieServices.findCategories();
			List<String> categories = new ArrayList<>();
			for (Category category : moviesCa) {
				categories.add(category.getName());
			}
			MoviesResponseDTO response = new MoviesResponseDTO(moviesC,moviesT,moviesD,categories);
			model.addAttribute("m" ,response);
			return "movies";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/movies/{category}")
	public String getMovieByCategory(@PathVariable("category") String category, Model model){
		try {
			List<String> moviesC = movieServices.findCodesByCategory(category);
			List<String> moviesT = movieServices.findTitlesByCategory(category);
			List<Integer> moviesD = movieServices.findDurationByCategory(category);
			Category categor = movieServices.getCategory(category);
			List<String> categories = new ArrayList<>();
			for(Integer i = 0; i < moviesC.size(); i++) {
				categories.add(categor.getName());
			}
			MoviesResponseDTO response = new MoviesResponseDTO(moviesC,moviesT,moviesD,categories);
			model.addAttribute("m" ,response);
			return "movies";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/today")
	public String getScheduleToday(Model model){
		try {
			Date date = new Date(System.currentTimeMillis());
			List<Long> scheduleId = scheduleService.findIdByDate(date);
			List<Timestamp> scheduleT = scheduleService.findTimeByDate(date);
			List<Float> scheduleP = scheduleService.findPriceByDate(date);
			List<Integer> scheduleC = scheduleService.findCapacityByDate(date);
			List<Movie> scheduleM = scheduleService.findMovieByDate(date);
			List<String> movies = new ArrayList<>();
			for (Movie movie : scheduleM) {
				movies.add(movie.getTitle());
			}
			List<Integer> duration = new ArrayList<>();
			for (Movie movie : scheduleM) {
				duration.add(movie.getLength());
			}
			ScheduleResponseDTO response = new ScheduleResponseDTO(scheduleId, scheduleT, scheduleP, scheduleC, movies, duration);
			model.addAttribute("s" ,response);
			return "schedules";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/reserve")
	public String getReservationPage(Model model) {
		model.addAttribute("reservation", new BookingInfoDTO(""));
		model.addAttribute("delreservation", new BookingInfoDTO(""));
		return "reserve";
	}
	
	@PostMapping("/reserve")
	public ResponseEntity<MessageDTO> createReservation(
			@ModelAttribute(name="reservation")
			@Valid BookingInfoDTO bookingInfo,
			BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			User user = userService.getUserAuthenticated();
			movieServices.reservate(timestamp, user.getUsername(), Long.parseLong(bookingInfo.getCode()));
			return new ResponseEntity<>(
					new MessageDTO("Reservacion Registrada"),
					HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
						new MessageDTO("Error interno"),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/booking")
	public String getAllBooking(Model model){
		try {
			User user = userService.getUserAuthenticated();
			List<Long> bookingId = bookingService.findIdByUser(user);
			List<Timestamp> bookingT = bookingService.findTimeByUser(user);
			List<Schedule> bookingS = bookingService.findScheduleByUser(user);
			List<String> titles = new ArrayList<>();
			for (Schedule schedule : bookingS) {
				titles.add(schedule.getMovie().getTitle());
			}
			List<Integer> lengths = new ArrayList<>();
			for (Schedule schedule : bookingS) {
				lengths.add(schedule.getMovie().getLength());
			}
			List<String> categoies = new ArrayList<>();
			for (Schedule schedule : bookingS) {
				categoies.add(schedule.getMovie().getCategory().getName());
			}
			BookingResponseDTO response = new BookingResponseDTO(bookingId, bookingT, titles, lengths, categoies);
			model.addAttribute("b",response);
			return "booking";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@DeleteMapping("/reserve")
	public ResponseEntity<MessageDTO> deleteReservation(
			@ModelAttribute(name="delreservation")
			@Valid BookingInfoDTO bookingInfo,
			BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			User user = userService.getUserAuthenticated();
			movieServices.deleteReservation(user.getUsername(), Long.parseLong(bookingInfo.getCode()));
			return new ResponseEntity<>(
					new MessageDTO("Reservacion Eliminada"),
					HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
						new MessageDTO("Error interno"),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
}
