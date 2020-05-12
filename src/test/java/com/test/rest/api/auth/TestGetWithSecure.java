package com.test.rest.api.auth;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestGetWithSecure {

	@Test
	public void testGetWithoutAuth(){
		
		expect()
		.log()
		.all()
		.statusCode(HttpStatus.SC_OK)
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/secure/ping/hello");
	}
	
	@Test
	public void testGetWithAuth(){
		
		given()
		.log().all()
		.header("Authorization","Basic YWRtaW46d2VsY29tZQ==")
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/secure/ping/hello")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void testBasicAuth(){
		
		given()
		.auth()
		.preemptive()
		.basic("admin", "welcome")
		.log().all()
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/secure/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
		
		
	}
}
