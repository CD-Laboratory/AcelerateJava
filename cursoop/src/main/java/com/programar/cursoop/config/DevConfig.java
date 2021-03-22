package com.programar.cursoop.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.programar.cursoop.services.DBService;

// classe de configuracao do aplication-dev.properties
@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanciateDataBase() throws ParseException {
		
		//se esta validacao for verdadeira ele nao cria novamente o banco de dados
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instanciateTestDataBase();
		
		return true;
	}
	
}
