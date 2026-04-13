package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenGenerator;
import com.api.utils.ConfigManager;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.header("Authorization",AuthTokenGenerator.getToken(Role.FD))
		.contentType("")
		.when()
		.post("/master")
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.hasKey("mst_oem"))
		.log().all();
		
	
	}
	
	@Test
	public void masterAPINegativeTest() throws IOException {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType("")
		.when()
		.post("/master")
		.then()
		.statusCode(401)
		.log().all();
	
	}

}
