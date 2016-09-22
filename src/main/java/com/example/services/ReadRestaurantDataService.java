package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ReadRestaurantDataDAO;

@RestController
@RequestMapping("/data")
@CrossOrigin(allowCredentials="true", 
methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS,RequestMethod.DELETE},
allowedHeaders={"x-requested-with", "accept", "authorization", "content-type"}, 
exposedHeaders={"access-control-allow-headers", "access-control-allow-methods", "access-control-allow-origin", "access-control-max-age", "X-Frame-Options"})
public class ReadRestaurantDataService {

	@Autowired
	private ReadRestaurantDataDAO readRestaurantDataDAO;

	@RequestMapping("/boroughs")
	public List<String> getBoroughs() {
		return readRestaurantDataDAO.getAllBoroughs();
	}

	@RequestMapping("/cuisines")
	public List<String> getCuisines() {
		return readRestaurantDataDAO.getAllCuisines();
	}

	
}
