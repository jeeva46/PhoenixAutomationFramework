package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	
	
	@Test
	public void LoginAPITest() throws IOException {
		
		
		UserCredentials userCred = new UserCredentials("iamfd", "password");
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(userCred)
		.log().uri()
		.log().headers()
		.log().body()
		.when()
		.post("login")
		.then()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("json-schema/login-schema.json"))
		.log().all();
		
		
	}

}
