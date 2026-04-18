package com.api.utils;

import java.io.IOException;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import com.api.constant.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	
	
	public static RequestSpecification requestSpec() throws IOException {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setAccept(ContentType.JSON)
		.setContentType(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
		
	}
	
	public static RequestSpecification requestSpec(Object obj) throws IOException {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setAccept(ContentType.JSON)
		.setContentType(ContentType.JSON)
		.setBody(obj)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
		
	}
	
	public static RequestSpecification requestSpecAuth(Role role) throws IOException {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setAccept(ContentType.JSON)
		.setContentType(ContentType.JSON)
		.addHeader("Authorization", AuthTokenGenerator.getToken(role))
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.build();
		
		return requestSpecification;
		
	}
	
	
	
	
	
	
	public static ResponseSpecification responseSpec_OK() {
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.expectStatusCode(200)
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.expectStatusCode(statusCode)
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_HTML(int statusCode) {
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.HTML)
		.expectResponseTime(Matchers.lessThan(1000L))
		.expectStatusCode(statusCode)
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}

}
