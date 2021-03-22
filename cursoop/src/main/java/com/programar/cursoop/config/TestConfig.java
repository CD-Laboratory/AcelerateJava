package com.programar.cursoop.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.programar.cursoop.services.DBService;

// classe de configuracao do aplication-test.properties
@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciateDataBase() throws ParseException {
		
		dbService.instanciateTestDataBase();
		
		return true;
	}
	
}
