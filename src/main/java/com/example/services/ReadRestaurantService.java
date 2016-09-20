package com.example.services;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Restaurant;
import com.example.dao.ReadRestaurantDAO;
import com.example.dao.ReadRestaurantDataDAO;

@RestController
@RequestMapping("/get")
public class ReadRestaurantService {

	@Autowired
	private ReadRestaurantDAO readDAO;

	@Autowired
	private ReadRestaurantDataDAO readRestaurantDataDAO;

	@RequestMapping("/borough")
	public List<Restaurant> getRestaurantsByBorough(@QueryParam(value = "name") String name) {
		List<Restaurant> restaurants = readDAO.getRestaurantsByBorough(WordUtils.capitalize(name));
		return restaurants;
	}

	@RequestMapping("/cuisine")
	public List<Restaurant> getRestaurantsByCuisine(@QueryParam(value = "type") String type) {
		List<Restaurant> restaurants = readDAO.getRestaurantsByCuisine(WordUtils.capitalize(type));
		return restaurants;
	}

	@RequestMapping("/filter")
	public List<Restaurant> getRestaurantsByCuisineAndBorough(@QueryParam(value = "cuisine") String cuisine,
			@QueryParam(value = "borough") String borough) {
		return readDAO.getRestaurantsByCuisineAndBorough(StringUtils.capitalize(cuisine),
				StringUtils.capitalize(borough));
	}

	@RequestMapping("/name/{partName:.+}")
	public List<Restaurant> getRestauarantsWithNameAs(@PathVariable String partName) {
		return readRestaurantDataDAO.getAllRestaurantsWithLabel(WordUtils.capitalize(partName));
	}

	@RequestMapping("/grade/{grade}")
	public List<Restaurant> getRestaurantsBasedOnGrade(@PathVariable String grade) {
		return readRestaurantDataDAO.getAllRestaurantsWithGrade(StringUtils.capitalize(grade));
	}

	@RequestMapping("/nograde")
	public List<Restaurant> getRestaurantsWithNoGrade() {
		return readRestaurantDataDAO.getNotGraded();
	}
}
