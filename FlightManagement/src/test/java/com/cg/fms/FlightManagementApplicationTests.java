package com.cg.fms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.fms.dto.FlightSchedule;
import com.cg.fms.service.IFlightSchedule;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightManagementApplicationTests {

	@Autowired
	IFlightSchedule flightScheduleService;
	
	@Test
	public void contextLoads() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 4545 + "/Booking/SearchFlights/Mumbai/Ahmedabad/2020-10-05";
		URI uri = new URI(baseUrl);

		ResponseEntity<FlightSchedule[]> datalist = restTemplate.getForEntity(uri, FlightSchedule[].class);
		FlightSchedule[] data = datalist.getBody();
		assertNotEquals(2, data.length);
	}
	
	@Test
	public void searchFlights() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 4545 + "/Booking/SearchFlights/Hyderabad/Banglore/2020-10-01";
		URI uri = new URI(baseUrl);

		ResponseEntity<FlightSchedule[]> datalist = restTemplate.getForEntity(uri, FlightSchedule[].class);
		FlightSchedule[] data = datalist.getBody();
		assertEquals(1, data.length);

	}

}
