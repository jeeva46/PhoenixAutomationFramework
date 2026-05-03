package com.api.test.dataproviders;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;
import com.dataProviders.api.bean.UserBean;

public class LoginAPITest {
	
	
	@Test(description = "verify login api test",groups = {"e2e","regression"}, dataProviderClass = com.dataProviders.DataProvidersUtil.class, 
			dataProvider = "Login")
	public void LoginAPITest(UserBean userBean) throws IOException {
		
		
		given()
		.spec(SpecUtil.requestSpec(userBean))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("json-schema/login-schema.json"));
		
		
		
	}

}
