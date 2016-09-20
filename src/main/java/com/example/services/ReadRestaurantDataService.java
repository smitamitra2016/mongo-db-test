package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ReadRestaurantDataDAO;

@RestController
@RequestMapping("/data")
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
