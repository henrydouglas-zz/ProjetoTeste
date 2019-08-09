package com.texoit.apigoldenraspeberry.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.texoit.apigoldenraspeberry.entity.MovieInformation;

public class ImportCSV {
	private static final Logger log = LoggerFactory.getLogger(ImportCSV.class);

	private static final List<String> extensions = new ArrayList<>();
	
	static {
		extensions.add("CSV");
	}
	
	public boolean isValidExtension(String extension) {
		if (extension != null && extensions.contains(extension)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Collection<MovieInformation> importCSV() throws IOException {
		
		//leitura do arquivo... monta entidade e grava no banco de dados na memória.
		return readFiles();
	}
	
	private Collection<MovieInformation> readFiles() throws IOException {
		
		Collection<MovieInformation> result = new ArrayList<>();
		
		File directory = new File("C:\\apiGoldenRaspeberry");
		File[] listFiles = directory.listFiles();
		String extension = null;
		
		if (listFiles.length > 0) {
			for (File file : listFiles) {
				if (file.getName().lastIndexOf(".") >= 0) {
					extension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toUpperCase();
				}
				if (this.isValidExtension(extension)) {
					FileInputStream inputFile = new FileInputStream(file);
					BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputFile, "ISO-8859-1"));
					
					result.addAll(this.validateAndConstruct(file, buffReader));
					
				} else {
					String error = "O arquivo "+file.getName()+" não é do formato CSV.";
					log.info(error);
				}
			}
		}
		return result;
	}
	
	private Collection<MovieInformation> validateAndConstruct(File file, BufferedReader dataFile) {
		Collection<MovieInformation> lines = new ArrayList<>();
		String lineFile = "";
		int lineNumber = 0;
		
		if (!dataFile.toString().isEmpty()) {
			try {
				while ((lineFile = dataFile.readLine()) != null) {
					lineNumber++;
					if (lineNumber == 1) {
						continue;
					}
					
					if (!lineFile.trim().equals("")) {
						String values[] = lineFile.split(";");
						if (lineFile.indexOf(";") == 4) {
							lines.add(this.generateFilePOJO(values));
						} else {
							String error = "A linha "+lineNumber+" do arquivo "+file.getName()+" contém colunas fora do padrão.";
							log.error(error);
						}
					}
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}			
		} else {
			String error = "O arquivo '"+file.getName()+"' está vazio!";
			log.info(error);
		}
		
		return lines;
	}
	
	private MovieInformation generateFilePOJO(String[] line) {
		MovieInformation lineData = new MovieInformation();
		lineData.setYear(Long.valueOf(line[0]));
		lineData.setMovieTitle(line[1]);
		lineData.setStudio(line[2]);
		lineData.setProducer(line[3]);
		lineData.setWinner(line.length == 5 && (line[4]).toLowerCase().equals("yes") ? true : false);
		
		return lineData;
	}
}
