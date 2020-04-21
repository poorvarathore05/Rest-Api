package com.test.rest.api;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.test.rest.RestResponse;

public class GetRequest {

	public static void main(String[] args){
		
		/*HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
		try(CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(get))
			{
			StatusLine status = response.getStatusLine();
			System.out.println(status.getStatusCode());
			System.out.println(status.getProtocolVersion());
			ResponseHandler<String> body = new BasicResponseHandler();
			String getBody= body.handleResponse(response);
			System.out.println(getBody);
			ResponseHandler<String> body = new BasicResponseHandler();
            RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
            System.out.println(restResponse.toString());
			}
		catch (Exception e) {
			e.printStackTrace();
		}*/
//		RestResponse response =RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
//		System.out.println(response);
		
		String jsonBody ="{"+
                            "\"BrandName\": \"Dell\"," +
                             "\"Features\": {" +
                               "\"Feature\": [\"8GB RAM\"," +
                                 "\"1TB Hard Drive\"]"+
                                "},"+
                                 "\"Id\":"+(int)(1000*(Math.random()))+","+
                                "\"LaptopName\": \"Latitude\"" +
                                    "}";
	
	   /* HttpPost post = new HttpPost("http://localhost:8080/laptop-bag/webapi/api/add");
	    try (CloseableHttpClient client = HttpClientBuilder.create().build()){
	    	post.addHeader("Content-Type","application/json"); 
	    	post.addHeader("Accept","application/json");
	    	//StringEntity data = new StringEntity(jsonBody,ContentType.APPLICATION_JSON);
	    	//post.setEntity(data);
	    	File file = new File("TestDataFile");
	    	FileEntity data = new FileEntity(file,ContentType.APPLICATION_JSON);
	    	post.setEntity(data);
	    	CloseableHttpResponse response =client.execute(post);
	    	ResponseHandler<String> handles = new BasicResponseHandler();
	    	RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handles.handleResponse(response));
	    	System.out.println(restResponse.toString());
	    	
			} catch (Exception e) {
			
		}*/
		/*Map<String ,String>header = new LinkedHashMap<String,String>();
		header.put("Accept", "application/json");
		header.put("Content-Type","application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonBody,ContentType.APPLICATION_JSON, header);
		System.out.println(response.getStatusCode());
		System.out.println(response.getResponseBody());*/
		/*
		HttpUriRequest delete =  RequestBuilder.delete("http://localhost:8080/laptop-bag/webapi/api/delete/170").build();
		try (CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse response = client.execute(delete)){
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restresponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		    System.out.println(restresponse.toString());	
		 			
 		} catch (Exception e) {
			e.printStackTrace();
					}*/
		
		String xmlbody = "<Laptop>" + "<BrandName>Dell</BrandName>"
				         + "<Features>" + "<Feature>8GB RAM</Feature>"
				         + "<Feature>1TB Hard Drive</Feature>"
				         + "<Feature>15.5 inch LCD</Feature>"
				         + "<Feature>1024 GB of SSD</Feature>"
				         + "<Feature>4GB of Graphic card</Feature>"
				         + "<Feature>This is Put</Feature>" + "</Features>"
				         + "<Id>656</Id>" + "<LaptopName>Latitude S Series</LaptopName>"
				         + "</Laptop>";
	    Map<String,String> headers = new LinkedHashMap<String,String>();
	    headers.put("Accept","application/xml");
	    headers.put("Content-Type", "application/xml");
		RestResponse restresponse= RestApiHelper.performPutRequest("http://localhost:8080/laptop-bag/webapi/api/update",xmlbody,ContentType.APPLICATION_XML,headers);
		System.out.println(restresponse);
	
	}	

}
