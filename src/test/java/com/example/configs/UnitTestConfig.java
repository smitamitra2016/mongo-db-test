package com.example.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

@Configuration
public class UnitTestConfig extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "mydb";
	}

	@Override
	public Mongo mongo() throws Exception {
		 return new EmbeddedMongoBuilder()
		            .version("2.6.1")
		            .bindIp("127.0.0.1")
		            .port(12345)
		            .build();
	}

}
