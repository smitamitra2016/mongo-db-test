package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

@Configuration
@EnableMongoRepositories
public class UnitTestConfig extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "mydb";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		 return new EmbeddedMongoBuilder()
		            .version("2.6.1")
		            .bindIp("127.0.0.1")
		            .port(12345)
		            .build();
	}
	


}
