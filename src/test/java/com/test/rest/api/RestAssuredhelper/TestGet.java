package com.test.rest.api.RestAssuredhelper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.asynchttpclient.uri.Uri;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;

public class TestGet extends BaseClass {
	
	@Test
	public void testGet() throws URISyntaxException{
		
		//URI url = new URI("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
		//Response response = when().get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"));
		Response response = given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/all"));
		System.out.println(response.asString());
		
	}
  
	@Test
	public void testStatusCode() throws URISyntaxException{
		
		 given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/all"))
	    .then().assertThat().statusCode(HttpStatus.SC_OK);
		
		//Assert.assertEquals(HttpStatus.SC_OK,code);
	}
	
	@Test
	public void testGetWithId() throws URISyntaxException{
		
		Map<String, String> headers = new HashMap<String,String>();
		headers.put("Accept", "application/json");
		
		String body = given()
		.headers(headers)
		.when()
		.get(new URI("/find/126"))
		.thenReturn().body().asString();
		System.out.println(body);
	}
	
	@Test
	public void testContent(){
		
		System.out.println(baseURI +port + basePath  );
		given().accept(ContentType.JSON)
		.when()
		.get("/find/126")
		.then()
		.body("BrandName",containsString("Dell"),"Id",equalTo("126"));
	}
}
