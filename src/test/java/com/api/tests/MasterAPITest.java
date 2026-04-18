package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenGenerator;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
		.spec(SpecUtil.requestSpecAuth(Role.FD))
		.when()
		.post("/master")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.hasKey("mst_oem"));
		
	
	}
	
	@Test
	public void masterAPINegativeTest() throws IOException {
		
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post("/master")
		.then()
		.spec(SpecUtil.responseSpec_HTML(401));
	}

}
