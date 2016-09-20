package com.example.dao;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.configs.UnitTestConfig;

@ActiveProfiles({ "test"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UnitTestConfig.class })
public class TestDAO {

	@Autowired
    private MongoTemplate mongoTemplate;

    protected void importJSON(String collection, String file) {
        try {
            for (Object line : FileUtils.readLines(new File(file), "utf8")) {
                mongoTemplate.save(line, collection);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not import file: " + file, e);
        }
    }
    
    @Before
    public void setup() {
        importJSON("restaurants", "src\\test\\resources\\restaurants.json");
    }
	
	@Autowired
	private ReadRestaurantDAO readRestaurantDAO;
	
	@Test
	public void testRetrievalByCuisine(){
		
	}
	
}
