package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.beans.Restaurant;

public interface ReadRestaurantDAO extends MongoRepository<Restaurant, String>{

	public List<Restaurant> getRestaurantsByBorough(String borough);

	public List<Restaurant> getRestaurantsByCuisine(String cuisine);

	public List<Restaurant> getRestaurantsByCuisineAndBorough(String cuisine, String borough);

}
