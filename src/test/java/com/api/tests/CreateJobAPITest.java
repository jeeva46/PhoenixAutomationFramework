package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty;
import com.api.pojo.CreateJob;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenGenerator;
import com.api.utils.ConfigManager;
import com.api.utils.DateTimeUtil;
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
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getDateTime(10), "12024366123821", "12024366123821", "12024366123821", DateTimeUtil.getDateTime(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());      
		List<Problems> problems = new ArrayList<Problems>();
		Problems problem = new Problems(Problem.POOR_BATTERY_LIFE.getCode(), "poor battery");
		problems.add(problem);
		
		CreateJob job = new CreateJob(ServiceLocation.SERVICE_CENTER_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problems);
		
		
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
