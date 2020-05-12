package com.test.rest.api.Helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.test.rest.ResponseBoby;
import com.test.rest.RestResponse;
import com.test.rest.api.RestApiHelper;

import junit.framework.Assert;

public class TestPutMethod {

	@Test
	public void testput() throws JsonMappingException, JsonProcessingException,IOException{
		
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
		
		String xmlbody = "<Laptop>" + "<BrandName>Dell</BrandName>"
		         + "<Features>" + "<Feature>8GB RAM</Feature>"
		         + "<Feature>1TB Hard Drive</Feature>"
		         + "<Feature>15.5 inch LCD</Feature>"
		         + "<Feature>1024 GB of SSD</Feature>"
		         + "<Feature>4GB of Graphic card</Feature>"
		         + "<Feature>This is Put</Feature>" + "</Features>"
		         + "<Id>"+ id +"</Id>" + "<LaptopName>Latitude S Series</LaptopName>"
		         + "</Laptop>";
		
		Map<String, String> headers = new HashMap<String,String>();
		headers.put("Accept","application/json");
		headers.put("Content-Type", "application/json");
		RestResponse restresponse = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, restresponse.getStatusCode());
		headers.clear();
		headers.put("Accept","application/xml");
		headers.put("Content-Type", "application/xml");
		restresponse = RestApiHelper.performPutRequest("http://localhost:8080/laptop-bag/webapi/api/update", xmlbody,ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_OK, restresponse.getStatusCode());
		restresponse = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find"+id, headers);
		XmlMapper mapper = new XmlMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ResponseBoby body=mapper.readValue(restresponse.getResponseBody(),ResponseBoby.class );
		
		Assert.assertEquals("Latitude S Series", body.LaptopName);
		
		
	
		
	}
}
