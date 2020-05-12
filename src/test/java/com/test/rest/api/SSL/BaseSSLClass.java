package com.test.rest.api.SSL;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class BaseSSLClass {

	@BeforeClass
	public void setUp(){
		
		baseURI = "http://localhost";
		port = 8080;
		basePath = "laptop-bag/webapi/sslres";
		authentication = certificate("C:\\Users\\Rahul\\Downloads\\Resource\\Resource\\mykey.keystore", "password");
	}
}
