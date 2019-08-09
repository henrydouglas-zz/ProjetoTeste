package com.texoit.apigoldenraspeberry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.texoit.apigoldenraspeberry.controller.BaseLoader;

@AutoConfigureDataJpa
@SpringBootApplication
@EnableJpaRepositories
public class ApiGoldenRaspeberryApplication {
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiGoldenRaspeberryApplication.class, args);
		BaseLoader baseLoader = context.getBean(BaseLoader.class);
		baseLoader.loadBase();
	}
}