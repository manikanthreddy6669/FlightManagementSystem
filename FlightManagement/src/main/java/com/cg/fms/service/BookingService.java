package com.cg.fms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.BookingDao;
import com.cg.fms.dao.ScheduledFlights;
import com.cg.fms.dto.Booking;
import com.cg.fms.dto.FlightSchedule;

@Service
@Transactional
public class BookingService implements IBooking {

	@Autowired
	BookingDao bookingdao;

	@Autowired
	ScheduledFlights scheduleflights;

	// Add Booking details
	@Override
	public Booking makeBooking(Booking bookingDetails) {
		String scheduleid = bookingDetails.getFlightSchedule().getScheduleId();
		FlightSchedule s = scheduleflights.findById(scheduleid).get();
		int availableSeats = s.getAvailableSeats() - bookingDetails.getNoOfPassengers();
		//Updating available seats count
		s.setAvailableSeats(availableSeats);

		return bookingdao.save(bookingDetails);

	}

}
