package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.dao.ReadRestaurantDataDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoDbApplication.class)
public class MongoDbApplicationTests {

	@Test
	public void contextLoads() {
	}

}
