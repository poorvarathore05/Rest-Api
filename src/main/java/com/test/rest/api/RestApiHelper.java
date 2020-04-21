package com.test.rest.api;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.xml.ws.spi.http.HttpHandler;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.asynchttpclient.uri.Uri;
import org.openqa.selenium.Keys;
import org.apache.http.HttpResponse;
import com.test.rest.RestResponse;

import kotlin.jvm.Throws;
import org.apache.http.Header;
public class RestApiHelper {

	public static RestResponse performGetRequest(String url, Map<String, String> header) {
		try {
			return performGetRequest(new URI(url), header);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}
    
	private static Header[] getCustomHeader(Map<String ,String> header){
		
		Header[] customHeaders = new Header[header.size()];
		int i =0;
		for(String key :header.keySet()){
			customHeaders[i++] = new BasicHeader(key, header.get(key));
			}
		return customHeaders;
	
	}
	private static RestResponse performRequest(HttpUriRequest method) {
		CloseableHttpResponse response = null;
		try(CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(method);
			ResponseHandler<String> body = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
		} catch (Exception e) {
			if(e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(),e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	public static RestResponse performGetRequest(URI uri, Map<String, String> header) {

		HttpGet get = new HttpGet(uri);
		if (header != null) {
			for (String key : header.keySet()) {
				get.addHeader(key, header.get(key));

			}
		    get.setHeaders(getCustomHeader(header));
		}
		CloseableHttpResponse response = null;

		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(get);
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(),
					body.handleResponse(response));
			// System.out.println(restResponse.toString());
			return restResponse;
		} catch (Exception e) {
			if (e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			throw new RuntimeException(e.getMessage(), e);

		}

	}

	public static HttpEntity getHttpEntity(Object content, ContentType type) {

		if (content instanceof String)
			return new StringEntity((String) content, type);
		else if (content instanceof File)
			return new FileEntity((File) content, type);
		else
			throw new RuntimeException("Entity Type not found");
	}

	public static RestResponse performPostRequest(String url, Object content, ContentType type,
			Map<String, String> header) {
		try {
			return performPostRequese(new URI(url), content, type, header);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public static RestResponse performPostRequese(URI url, Object content, ContentType type,
			Map<String, String> header) {
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);

		/*if (header != null) {
			for (String key : header.keySet()) {
				post.addHeader(key, header.get(key));

			}
		}*/
		post.setHeaders(getCustomHeader(header));
		post.setEntity(getHttpEntity(content, type));
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(post);
			ResponseHandler<String> handles = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), handles.handleResponse(response));
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				return new RestResponse(response.getStatusLine().getStatusCode(), "");
			}
			throw new RuntimeException(e.getMessage(), e);
		}

	}
 
	public static RestResponse perfromDeleteRequest(String url , Map<String,String> headers){
		try {
			return perfromDeleteRequest(new URI(url), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
	}
     public static RestResponse perfromDeleteRequest(URI uri , Map<String,String> headers){
		
		HttpUriRequest delete = RequestBuilder.delete(uri).build();
		if(headers !=null){
			delete.setHeaders(getCustomHeader(headers));
		}
		return performRequest(delete);
	}
	
     public static RestResponse performPutRequest(String url ,Object content,ContentType type,Map<String,String> headers){
    	 try {
			return performPutRequest(new URI(url), content,type, headers);
		} catch (URISyntaxException e) {
		 throw new RuntimeException(e.getMessage(),e);	
				}
     }
     

	public static RestResponse performPutRequest(URI uri ,Object content,ContentType type, Map<String,String> headers){
    	  
    	 HttpUriRequest put = RequestBuilder.put(uri).setEntity(getHttpEntity(content,type)).build();
    	 
    	 if(headers !=null){
    		 put.setHeaders(getCustomHeader(headers));
    	 }
    	 return performRequest(put);
     }
}
