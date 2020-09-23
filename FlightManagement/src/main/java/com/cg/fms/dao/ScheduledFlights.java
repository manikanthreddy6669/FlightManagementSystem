package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fms.dto.FlightSchedule;

public interface ScheduledFlights extends JpaRepository<FlightSchedule, String> {

}
