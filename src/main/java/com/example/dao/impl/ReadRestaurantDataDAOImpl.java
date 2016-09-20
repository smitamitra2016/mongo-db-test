package com.example.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.beans.Restaurant;
import com.example.dao.ReadRestaurantDataDAO;

@Repository
public class ReadRestaurantDataDAOImpl implements ReadRestaurantDataDAO{

	@Autowired
	private MongoTemplate mongo;
	
	
	@Override
	public List<String> getAllBoroughs() {
		return mongo.getCollection("restaurants").distinct("borough");
	}

	@Override
	public List<String> getAllCuisines() {
		return mongo.getCollection("restaurants").distinct("cuisine");
	}

	@Override
	public List<Restaurant> getAllRestaurantsWithGrade(String grade) {
		Query query = new Query();
		query.addCriteria(Criteria.where("grades.0.grade").is(grade));
		return mongo.find(query, Restaurant.class);
	}

	@Override
	public List<Restaurant> getAllRestaurantsWithLabel(String namePart) {
		Pattern pattern = Pattern.compile(namePart);
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(pattern));
		return mongo.find(query, Restaurant.class);
	}

	@Override
	public List<Restaurant> getNotGraded() {
		Pattern pattern = Pattern.compile("[A-Z]{1}");
		Query query = new Query();
		query.addCriteria(Criteria.where("grades.0.grade").not().regex(pattern));
		return mongo.find(query, Restaurant.class);
	}

}
