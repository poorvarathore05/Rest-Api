
package com.test.rest.api.RestAssuredhelper;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


public class TestQueryParam extends BaseClass{
	
	@Test
	public void testQuery(){

     String str=  given()
      .accept(ContentType.JSON)
      .param("Id","126" )
	  .param("BrandName","Dell")
	  .when()
	  .get("/query")
	  .thenReturn()
	  .asString();
     System.out.println(str);
	}

}
