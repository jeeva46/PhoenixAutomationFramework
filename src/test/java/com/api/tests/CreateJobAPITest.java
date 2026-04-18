package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJob;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenGenerator;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() throws IOException {
		
		
		Customer customer = new Customer("ash", "a", "1234567898", "", "ash@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("33a", "omr", "omr", "omr", "omr", "4321234", "India", "Tamil Nadu");
		CustomerProduct customerProduct = new CustomerProduct("2026-03-04T18:30:00.000Z", "12328766113821", "12328766113821", "12328766113821", "2026-03-04T18:30:00.000Z", 3, 3);      
		List<Problems> problems = new ArrayList<Problems>();
		Problems problem = new Problems(2, "poor battery");
		problems.add(problem);
		
		CreateJob job = new CreateJob(0, 2, 1, 2, customer, customerAddress, customerProduct, problems);
		
		
		given()
		.spec(SpecUtil.requestSpec(job, Role.FD))
		.body(job)
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-schema/CreateJobSchema.json"))
		.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
