package com.api.utils;

import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenGenerator {

	public static String getToken(Role role) throws IOException {
		
		UserCredentials user=null;
		
		if(role == Role.FD) {
			user = new UserCredentials("iamfd", "password");
		}
		else if(role == Role.SUP) {
			user = new UserCredentials("iamsup", "password");
		}
		else if(role == Role.ENG) {
			user = new UserCredentials("iameng", "password");
		}
		else if(role == Role.QC) {
			user = new UserCredentials("iamqc", "password");
		}
		
		
		String token = given().baseUri(getProperty("BASE_URI")).and().contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(user)

				.when().post("login").then().log().ifValidationFails().extract().jsonPath().getString("data.token");
		
		return token;
	}

}
