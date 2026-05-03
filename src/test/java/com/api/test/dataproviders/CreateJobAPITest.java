package com.api.test.dataproviders;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
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

	
	@Test(description = "createjob api test",groups = {"e2e","regression"},
			dataProvider = "CreateJobDataProvider",dataProviderClass = com.dataProviders.DataProvidersUtil.class)
	public void createJobAPITest(CreateJob payload) throws IOException {
		
		
		
		given()
		.spec(SpecUtil.requestSpec(payload, Role.FD))
		.body(payload)
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
