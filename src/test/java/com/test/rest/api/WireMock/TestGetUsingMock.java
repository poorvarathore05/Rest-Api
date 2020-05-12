package com.test.rest.api.WireMock;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

/*import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;*/

import io.restassured.RestAssured;

public class TestGetUsingMock {
	
	private static final int Port = 8080 ;
	private static final String Host = "localhost";
	
	private static WireMockServer server = new WireMockServer(Port);
	
	@BeforeClass
	public void setup(){
		
		ResponseDefinitionBuilder response = new ResponseDefinitionBuilder();
		response.withStatus(200);
		server.start();
		WireMock.configureFor(Host, Port);
		WireMock.stubFor(WireMock.get("/laptop-bag/webapi/api/all").willReturn(response));
	}
	@Test
	public void testGetEndPoint(){
		
		RestAssured.given()
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/api/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
	}
	
	@AfterClass
	public void stopServer(){
		if(null!=server && server.isRunning())
			server.shutdownServer();
	}

}
