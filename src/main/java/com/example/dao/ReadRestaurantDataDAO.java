package com.example.dao;

import java.util.List;

import com.example.beans.Restaurant;

public interface ReadRestaurantDataDAO {

	public List<String> getAllBoroughs();

	public List<String> getAllCuisines();

	public List<Restaurant> getAllRestaurantsWithGrade(String grade);

	public List<Restaurant> getAllRestaurantsWithLabel(String namePart);
	
	public List<Restaurant> getNotGraded();

}
