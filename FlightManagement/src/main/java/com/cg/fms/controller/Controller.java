package com.cg.fms.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.Exception.FlightsNotFound;
import com.cg.fms.dto.Booking;
import com.cg.fms.dto.FlightSchedule;
import com.cg.fms.service.IBooking;
import com.cg.fms.service.IFlightSchedule;

@RestController
@RequestMapping("/Booking")
@CrossOrigin("http://localhost:4200")
public class Controller {

	@Autowired
	IBooking bookingService;

	@Autowired
	IFlightSchedule flightScheduleService;

	// Make Booking
	@PostMapping(value = "/BookingTickets")
	public ResponseEntity<String> addBookingDetails(@RequestBody() Booking booking) {

		// Check whether seats are available or not
		if (booking.getNoOfPassengers() <= booking.getFlightSchedule().getAvailableSeats()) {
			Booking b = bookingService.makeBooking(booking);
			if (b.getBookingId() > 0)
				return new ResponseEntity<String>("bookingId:" + b.getBookingId(), HttpStatus.OK);
			else
				return new ResponseEntity<String>("booking unsuccesful", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Seats not available", HttpStatus.OK);

	}

	// Search Flights
	@GetMapping("/SearchFlights/{source}/{destination}/{date}")
	public ResponseEntity<List<FlightSchedule>> searchForFlights(@PathVariable("source") String fromLocation,
			@PathVariable("destination") String toLocation, @PathVariable("date") Date date) throws FlightsNotFound {

		List<FlightSchedule> scheduleList = flightScheduleService.getScheduleFlights(fromLocation, toLocation, date);
		if (!scheduleList.isEmpty())
			return new ResponseEntity<>(scheduleList, HttpStatus.OK);
		else
			throw new FlightsNotFound("no records found");

	}

	@ExceptionHandler(FlightsNotFound.class)
	public ResponseEntity<String> flightsNotFound(FlightsNotFound e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	}

}
