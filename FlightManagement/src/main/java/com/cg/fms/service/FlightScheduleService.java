package com.cg.fms.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.FlightScheduleDao;
import com.cg.fms.dto.FlightSchedule;

@Service
@Transactional
public class FlightScheduleService implements IFlightSchedule {

	@Autowired
	FlightScheduleDao flightSchedule;

	// Searching for Flights
	@Override
	public List<FlightSchedule> getScheduleFlights(String fromLocation, String toLocation, Date date) {
		List<FlightSchedule> availableFlights = new ArrayList<>();
		// Storing all scheduled flights data
		List<FlightSchedule> flightList = flightSchedule.findAll();
		// Filtering results with given input
		for (FlightSchedule flightRoute : flightList)
			if (flightRoute.getSource().getArea().equalsIgnoreCase(fromLocation)
					&& flightRoute.getDestination().getArea().equalsIgnoreCase(toLocation)
					&& flightRoute.getDeparture().toLocalDate().equals(date.toLocalDate())) {
				availableFlights.add(flightRoute);
			}

		return availableFlights;

	}

}
