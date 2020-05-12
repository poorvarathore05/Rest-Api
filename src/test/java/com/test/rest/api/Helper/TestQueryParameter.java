package com.test.rest.api.Helper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.Test;

import com.test.rest.RestResponse;
import com.test.rest.api.RestApiHelper;

public class TestQueryParameter {

	
	@Test
	public void queryParameter() throws URISyntaxException{
		
		URI uri = new URIBuilder()
	    .setScheme("http")
		.setHost("localhost:8080")
		.setPath("laptop-bag/webapi/api/query/")
		.setParameter("id", "121")
		.setParameter("LaptopName", "Latitude").build();
		
		RestResponse response = RestApiHelper.performGetRequest(uri,null);
		System.out.println(response.toString());
	}
}
