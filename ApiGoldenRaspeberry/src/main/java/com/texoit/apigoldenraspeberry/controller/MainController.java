package com.texoit.apigoldenraspeberry.controller;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.apigoldenraspeberry.entity.ResultApiPOJO;
import com.texoit.apigoldenraspeberry.repository.MovieInformationRepository;

@RestController
@RequestMapping(value="/apiGoldenRaspeberry", produces="application/json")
public class MainController {
	
	@Autowired
	private MovieInformationRepository movieInformationRepository;
	
	@GetMapping
	public String mainController() {
		return listMovies(null);
	}
	
	@GetMapping(value="/{value}")
	public String getByValue(@PathVariable String value) {
		
		return listMovies(value);
	}
	
	public String listMovies(String value) {
		
		JSONObject result = new JSONObject();
		JSONArray arrayItem = null;
		JSONObject item = null;
		
		Collection<ResultApiPOJO> resultProducers = movieInformationRepository.getProducers();
		LinkedHashMap<String, ResultApiPOJO> hashProducers = new LinkedHashMap<>();
		LinkedHashMap<String, ResultApiPOJO> hashMinMax = new LinkedHashMap<>();
		ResultApiPOJO producerPOJO = null;
		
		// quebra os produtores conjuntos.
		for (ResultApiPOJO prod : resultProducers) {
			String[] producers = prod.getProducer().toLowerCase().replace(" and ", ",").split(",");
			for (String producerName : producers) {
				if (hashProducers.get(producerName.trim()) == null) {
					producerPOJO = new ResultApiPOJO(producerName.trim(), prod.getInterval(), prod.getPreviousWin(), prod.getFollowingWin(), prod.isWinner()); 
					hashProducers.put(producerName.trim(), producerPOJO);					
				} else {
					producerPOJO = hashProducers.get(producerName.trim());
					if (prod.getPreviousWin() < producerPOJO.getPreviousWin()) {
						producerPOJO.setPreviousWin(prod.getPreviousWin());
					}
					if (prod.getFollowingWin() > producerPOJO.getFollowingWin()) {
						producerPOJO.setFollowingWin(prod.getFollowingWin());
					}
					producerPOJO.setInterval(producerPOJO.getFollowingWin() - producerPOJO.getPreviousWin());
				}
			}
		}
		
		// retornar produtor com maior intervalo entre dois premios e o que teve duas premiações mais rapido.
		for (ResultApiPOJO prod : hashProducers.values()) {
			if (prod.getInterval() == 0) {
				// não ganhou mais de 1 prêmio
				continue;
			}
			
			ResultApiPOJO minProd = new ResultApiPOJO(prod.getProducer(), prod.getInterval(), prod.getPreviousWin(), prod.getFollowingWin(), prod.isWinner());
			if (hashMinMax.get("min") == null) {
				hashMinMax.put("min", minProd);
			} else {
				if (minProd.getInterval() < hashMinMax.get("min").getInterval()) {
					hashMinMax.put("min", minProd);
				}
			}
			
			ResultApiPOJO maxProd = new ResultApiPOJO(prod.getProducer(), prod.getInterval(), prod.getPreviousWin(), prod.getFollowingWin(), prod.isWinner());
			if (hashMinMax.get("max") == null) {			
				hashMinMax.put("max", maxProd);
			} else {
				if (maxProd.getInterval() > hashMinMax.get("max").getInterval()) {
					hashMinMax.put("max", maxProd);
				}
			}
		}
		
		try {
			ResultApiPOJO minMovie = hashMinMax.get("min");
			item = new JSONObject();
			arrayItem = new JSONArray();
			item.append("producer", minMovie.getProducer());
			item.append("interval", minMovie.getInterval());
			item.append("previousWin", minMovie.getPreviousWin());
			item.append("followingWin", minMovie.getFollowingWin());
			arrayItem.put(item);
			result.put("min", arrayItem);
			
			ResultApiPOJO maxMovie = hashMinMax.get("max");
			item = new JSONObject();
			arrayItem = new JSONArray();
			item.append("producer", maxMovie.getProducer());
			item.append("interval", maxMovie.getInterval());
			item.append("previousWin", maxMovie.getPreviousWin());
			item.append("followingWin", maxMovie.getFollowingWin());
			arrayItem.put(item);
			result.put("max", arrayItem);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if (value != null) {
			try {
				return result.getString(value).toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} 
		return result.toString();
	}
}
