package com.test.rest.api;

import java.security.Principal;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.test.rest.RestResponse;

public class PromptAuth {

	public static void main(String[] args) {

		/*CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "welcome"));
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(provider);

		HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/prompt/all");

		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(get)) {

			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restresponse = new RestResponse(response.getStatusLine().getStatusCode(),
					handler.handleResponse(response));
			System.out.println(restresponse.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		RestResponse response = HttpsClientHelper.PerformGetHttpWithSSl("http://localhost:8080/laptop-bag/webapi/sslres/all", null);
		System.out.println(response.toString());
	}
}
