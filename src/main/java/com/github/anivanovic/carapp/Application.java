package com.github.anivanovic.carapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.github.anivanovic.carapp.repository"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		Hibernate5Module module = new Hibernate5Module();
		module.disable(Feature.USE_TRANSIENT_ANNOTATION);
		module.enable(Feature.FORCE_LAZY_LOADING);
		module.disable(Feature.REQUIRE_EXPLICIT_LAZY_LOADING_MARKER);
		mapper.registerModule(module);

		return mapper;
	}

}
