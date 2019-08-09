package com.texoit.apigoldenraspeberry.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.apigoldenraspeberry.entity.MovieInformation;
import com.texoit.apigoldenraspeberry.repository.MovieInformationRepository;

@RestController
public class BaseLoader {
	
	@Autowired
	private MovieInformationRepository movieInformationRepository;
	
	@Autowired
	private ImportCSV importCSV;
	
	public void loadBase() {
		try {			
			Collection<MovieInformation> listData = importCSV.importCSV();
			movieInformationRepository.saveAll(listData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
