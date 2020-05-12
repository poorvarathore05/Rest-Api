package com.test.rest.api.Helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.rest.ResponseBoby;
import com.test.rest.RestResponse;
import com.test.rest.api.RestApiHelper;

public class TestGetMethod {
	
	
	@Test
	public void testGetPing(){
		
		String url = "http://localhost:8080/laptop-bag/webapi/api/ping/hello";
		Map<String,String>header = new HashMap<String,String>();
		RestResponse response = RestApiHelper.performGetRequest(url,header);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		Assert.assertEquals("Hi! hello",response.getResponseBody() );
	}
   
	@Test
	public void testGetAll(){
		
		String url ="http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String,String>header = new HashMap<String,String>();
		RestResponse response = RestApiHelper.performGetRequest(url,header);
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || HttpStatus.SC_NO_CONTENT== response.getStatusCode(),"Expected content Not Found");
		System.out.println(response.getResponseBody());
	}
	
	@Test
	public void testGetId(){
		
		String url ="http://localhost:8080/laptop-bag/webapi/api/Id";
		Map<String,String>header = new HashMap<String,String>();
		RestResponse response = RestApiHelper.performGetRequest(url,header);
		Assert.assertTrue( HttpStatus.SC_OK==response.getStatusCode() || HttpStatus.SC_NOT_FOUND== response.getStatusCode(),"Expected content Not Found");
		//System.out.println(response.getResponseBody());
		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.serializeNulls().setPrettyPrinting().create();
		ResponseBoby body = gson.fromJson(response.getResponseBody(), ResponseBoby.class);
		System.out.println(body);
		
	}
}
