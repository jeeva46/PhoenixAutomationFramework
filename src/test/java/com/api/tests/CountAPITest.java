package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenGenerator.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {
	
	
	@Test
	public void countAPITest() throws IOException {
	
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.header("Authorization",getToken(FD))
		.log().all()
		.when()
		.get("/dashboard/count")
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
		.body("message",Matchers.equalTo("Success"))
		.body("data", Matchers.notNullValue())
		.body("data.size", Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)));
		
		
		
	}
	
	
	@Test
	public void countAPINegativeTest() throws IOException {
	
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.log().all()
		.when()
		.get("/dashboard/count")
		.then()
		.statusCode(401)
		.log().all();
		
		
		
	}

}
