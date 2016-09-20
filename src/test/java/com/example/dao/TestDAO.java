package com.example.dao;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

	@Autowired
	private ReadRestaurantDAO readRestaurantDAO;

	@Test
	public void testRetrievalByCuisine() {
		// Given
		final String cuisine = "Delicatessen";
		// When
		List<Restaurant> restaurants = this.readRestaurantDAO.getRestaurantsByCuisine(cuisine);
		// Then
		assertNotNull("The restaurant list should not be null", restaurants);
	}

}
