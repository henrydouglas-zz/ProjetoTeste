package com.texoit.apigoldenraspeberry.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

	@Autowired
	public WebApplicationContext appContext;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}
	
	@Test
	public void testReturnAll() {
		String url = "/apiGoldenRaspeberry";
		try {
			this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("min", !equals(null)))
				.andExpect((ResultMatcher) jsonPath("max", !equals(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReturnMin() {
		String url = "/apiGoldenRaspeberry/min";
		try {
			this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("", !equals(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReturnMax() {
		String url = "/apiGoldenRaspeberry/max";
		try {
			this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("", !equals(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReturnNotExists() {
		String url = "/apiGoldenRaspeberry/anything";
		try {
			this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
