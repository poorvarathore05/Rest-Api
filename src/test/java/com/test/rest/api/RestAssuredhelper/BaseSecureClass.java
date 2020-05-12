package com.test.rest.api.RestAssuredhelper;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;
import io.restassured.authentication.PreemptiveAuthProvider;

public class BaseSecureClass {
	
	@BeforeClass
	public static void setup(){
		
	    baseURI = "http://localhost";
	    port   =  8080;
	    basePath ="/laptop-bag/webapi/api";
	    authentication = preemptive().basic("admin","welcome");
	   
	    
	}

}
