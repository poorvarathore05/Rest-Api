package com.test.rest.api.SSL;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestGetWithSSl extends BaseSSLClass {
	
	@Test
    public void testGet(){

		String s =given()
				.relaxedHTTPSValidation()
		.accept(ContentType.XML)
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/sslres/all")
		.thenReturn()
		.asString();
		System.out.println(s);
		
	}
	
	@Test
	public void testGetWithCertificate(){
		
		String s =given()
		.log().all()
		.when()
		.get("/all")
		.thenReturn().asString();
		System.out.println(s);
	}
}
