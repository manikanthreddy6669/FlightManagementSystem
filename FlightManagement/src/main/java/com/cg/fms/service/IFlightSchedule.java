package com.cg.fms.service;

import java.sql.Date;
import java.util.List;

import com.cg.fms.dto.FlightSchedule;

public interface IFlightSchedule {

	List<FlightSchedule> getScheduleFlights(String fromLocation, String toLocation, Date date);

}
