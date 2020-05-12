package com.test.rest.api;

import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class HttpAsyncClientHelper {

	public static Header[] getCustomHeader(Map<String, String>headers){
		
		Header[] customHeader = new Header[headers.size()];
		int i =0;
		for (String key : headers.keySet()) {
			customHeader[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeader;
		
	} 
	
	
}
