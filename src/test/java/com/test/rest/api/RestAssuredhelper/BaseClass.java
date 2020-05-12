package com.test.rest.api.RestAssuredhelper;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseClass {
	
	@BeforeClass
	public static void setup(){
		
	    baseURI = "http://localhost";
	    port   =  8080;
	    basePath ="/laptop-bag/webapi/api";
	    
	    
	}

}
