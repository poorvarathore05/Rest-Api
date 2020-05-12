package com.test.rest.api.RestAssuredhelper;

import static org.hamcrest.Matcher.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.test.rest.api.Helper.Features;
import com.test.rest.api.Helper.LaptopBag;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestPost extends BaseClass {
	
	
	@Test
	public void post(){
		
		int id = (int)(1000*(Math.random()));
	 String jsonBody ="{"+
                "\"BrandName\": \"Dell\"," +
                 "\"Features\": {" +
                   "\"Feature\": [\"8GB RAM\"," +
                     "\"1TB Hard Drive\"]"+
                    "},"+
                     "\"Id\":"+id+","+
                    "\"LaptopName\": \"Latitude\"" +
                        "}";
		
	  given()
	  .accept(ContentType.JSON).with()
	 .contentType(ContentType.JSON)
	 .and()
	 .body(jsonBody)
	 .when()
	 .post("/add")
	 .then()
	 .assertThat()
	 .statusCode(HttpStatus.SC_OK);
		
	}

	@Test
	public void testMappingClass(){
		
		String id = (int)(1000*(Math.random()))+"";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Dell");
		bag.setId(id);
		bag.setLaptopName("Latitude");
		Features feat = new Features();
	    feat.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
	    bag.setFeatures(feat);
		
		given()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.XML)
		.when()
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
}
