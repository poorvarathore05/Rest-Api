package com.test.rest.api;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.test.rest.RestResponse;

public class HttpsClientHelper {
	
	public static SSLContext getSSlContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		
		TrustStrategy trust = new TrustStrategy() {
			
			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				
				return true;
			}
		};
		return SSLContextBuilder.create().loadTrustMaterial(trust).build();
	}

	public static CloseableHttpClient getHttpClient(SSLContext sslcontext){
		return HttpClientBuilder.create().setSSLContext(sslcontext).build();
	}
	
	public static RestResponse PerformGetHttpWithSSl(String uri, Map<String,String>headers){
		HttpGet get = new HttpGet(uri);
		
		if(headers!=null){
			for (String key : headers.keySet()) {
            get.addHeader(key, headers.get(key));
        	}
		}
		CloseableHttpResponse response = null;
		try (CloseableHttpClient client = getHttpClient(getSSlContext())){
			response = client.execute(get);
			ResponseHandler<String>handler = new BasicResponseHandler();
			RestResponse restresponse = new RestResponse(response.getStatusLine().getStatusCode(),handler.handleResponse(response));
			return restresponse;
		} catch (Exception e) {
			if(e  instanceof HttpResponseException) {
			 return new RestResponse(response.getStatusLine().getStatusCode(),e.getMessage());	
			}
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
