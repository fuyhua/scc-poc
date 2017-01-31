package com.example.test;

import static org.junit.Assert.assertTrue;
import org.junit.Before;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.example.PersonController;


public class PersonControllerBase {
	
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new PersonController());
	}
	
	public void isValidAge(int value) {
		assertTrue((value >= 0 ) && (value <= 120));
	}

	
}
