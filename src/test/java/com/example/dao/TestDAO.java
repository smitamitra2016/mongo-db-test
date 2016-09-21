package com.example.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.MongoDbApplication;
import com.example.beans.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MongoDbApplication.class)
@WebAppConfiguration
@TestPropertySource(properties = "spring.data.mongodb.port = 37017")
public class TestDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Value("${test.database.file}")
	private String testDatabaseFile;

	protected void importJSON(String collection) {
		try {
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(this.testDatabaseFile);
			File file = resource.getFile();
			for (Object line : FileUtils.readLines(file, "utf8")) {
				this.mongoTemplate.save(line, collection);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not import file: " + this.testDatabaseFile, e);
		}
	}

	@Before
	public void setup() {
		 importJSON("restaurants");
	}
	
	@After
	public void removeAll(){
		this.mongoTemplate.dropCollection("restaurants");
	}

	@Autowired
	private ReadRestaurantDAO readRestaurantDAO;
	
	@Autowired
	private ReadRestaurantDataDAO readRestaurantDataDAO;
	
	@Test
	public void testGetNotGraded(){
		List<Restaurant> restaurants = this.readRestaurantDataDAO.getNotGraded();
		System.out.println("testGetNotGraded"+restaurants.size());
	}

	@Test
	public void testGetAllRestaurantsWithLabel(){
		List<Restaurant> restaurants = this.readRestaurantDataDAO.getAllRestaurantsWithLabel("Foods");
		System.out.println("testGetAllRestaurantsWithLabel"+restaurants.size());
	}
	
	@Test
	public void testGetAllCuisines(){
		//When
		List<String> cuisines = this.readRestaurantDataDAO.getAllCuisines();
		//Then
		assertEquals("Unique cuisines ",cuisines.size(), 9);
	}
	
	@Test
	public void testGetAllRestaurantsWithGrade(){
		//When
		List<Restaurant> restaurants = this.readRestaurantDataDAO.getAllRestaurantsWithGrade("B");
		//Then
		assertEquals("Restaurants with grade B",restaurants.size(), 1);
	}
	
	@Test
	public void testGetAllBoroughs(){
		//When
		List<String> boroughs = this.readRestaurantDataDAO.getAllBoroughs();
		//Then
		assertEquals("Unique boroughs ",boroughs.size(), 5);
	}
	
	@Test
	public void testRetrievalByCuisine() {
		// Given
		String cuisine = "Delicatessen";
		// When
		List<Restaurant> restaurants = this.readRestaurantDAO.getRestaurantsByCuisine(cuisine);
		// Then
		assertNotNull("The restaurant list should not be null", restaurants);
		assertEquals("There should be one restaurant", 2, restaurants.size());
		assertEquals("The cuisine of the fetched restaurant should be the same as the value used to set up data",
				cuisine, restaurants.get(0).getCuisine());
	}
	
	@Test
	public void testRetrievalByBorough() {
		// Given
		String borough = "Manhattan";
		// When
		List<Restaurant> restaurants = this.readRestaurantDAO.getRestaurantsByBorough(borough);
		// Then
		assertNotNull("The restaurant list should not be null", restaurants);
		assertEquals("There should be one restaurant", 3, restaurants.size());
		assertEquals("The borough of the fetched restaurant should be the same as the value used to set up data",
				borough, restaurants.get(0).getBorough());
	}
	
	@Test
	public void testRetrievalByCuisineAndBorough() {
		// Given
		String borough = "Brooklyn";
		String cuisine = "Delicatessen";
		// When
		List<Restaurant> restaurants = this.readRestaurantDAO.getRestaurantsByCuisineAndBorough(cuisine, borough);
		// Then
		assertNotNull("The restaurant list should not be null", restaurants);
		assertEquals("There should be one restaurant", 2, restaurants.size());
		assertEquals("The cuisine of the fetched restaurant should be the same as the value used to set up data",
				borough, restaurants.get(0).getBorough());
	}

}
