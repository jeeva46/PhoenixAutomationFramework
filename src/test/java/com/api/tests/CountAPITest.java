package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenGenerator.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {
	
	
	@Test
	public void countAPITest() throws IOException {
	
		
		given()
		.spec(SpecUtil.requestSpecAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message",Matchers.equalTo("Success"))
		.body("data", Matchers.notNullValue())
		.body("data.size", Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)));
		
		
		
	}
	
	
	@Test
	public void countAPINegativeTest() throws IOException {
	
		
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_HTML(401));
		
		
		
	}

}
