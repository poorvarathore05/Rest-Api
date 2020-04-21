package com.test.rest.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.rest.ResponseBoby;
import com.test.rest.RestResponse;

import okhttp3.ResponseBody;


public class TestPostMethod {

	@Test
	public void testPost(){

		int id = (int)(1000*(Math.random()));
		String jsonBody ="{"+
                "\"BrandName\": \"Dell\"," +
                 "\"Features\": {" +
                   "\"Feature\": [\"8GB RAM\"," +
                     "\"1TB Hard Drive\"]"+
                    "},"+
                     "\"Id\":"+id+","+
                    "\"LaptopName\": \"Latitude\"" +
                        "}";
		
		Map<String ,String>header = new LinkedHashMap<String,String>();
		header.put("Accept", "application/json");
		header.put("Content-Type","application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add",jsonBody,ContentType.APPLICATION_JSON, header);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find"+id,header);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBoby body = gson.fromJson(response.getResponseBody(),ResponseBoby.class);
		Assert.assertEquals(id, body.Id);
		Assert.assertEquals("Latitude", body.LaptopName);
		
		
		
	}

	
}
