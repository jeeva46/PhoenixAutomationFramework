package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenGenerator.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class UserDetailAPITest {

	@Test
	public void userDetailTest() throws IOException {

		Header authHeader = new Header("Authorization",getToken(SUP));
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header(authHeader)
		.log().uri()
		.log().headers()
		.when()
		.get("userdetails")
		.then()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("json-schema/userDetails-schema.json"))
		.log().all();
		
		
	}	
}
