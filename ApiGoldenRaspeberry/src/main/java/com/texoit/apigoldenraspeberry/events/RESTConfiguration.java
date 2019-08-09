package com.texoit.apigoldenraspeberry.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.texoit.apigoldenraspeberry.controller.BaseLoader;
import com.texoit.apigoldenraspeberry.controller.ImportCSV;

@Configuration
public class RESTConfiguration {
	
	@Bean
	public ImportCSV importCSV() {
		return new ImportCSV();
	}
	
	@Bean
	public BaseLoader baseLoader() {
		return new BaseLoader();
	}
}
