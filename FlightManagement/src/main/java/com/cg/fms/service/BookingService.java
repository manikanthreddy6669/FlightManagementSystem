package com.cg.fms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.BookingDao;
import com.cg.fms.dto.Booking;

@Service
@Transactional
public class BookingService implements IBooking {

	@Autowired
	 BookingDao bookingdao;
	
	//Add Booking details
	@Override
	public Booking makeBooking(Booking bookingDetails) {
		return bookingdao.save(bookingDetails);

	}

}
