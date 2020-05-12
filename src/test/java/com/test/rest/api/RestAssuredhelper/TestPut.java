package com.test.rest.api.RestAssuredhelper;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.hamcrest.collection.HasItemInArray;
import org.testng.annotations.Test;

import com.test.rest.api.Helper.Features;
import com.test.rest.api.Helper.LaptopBag;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestPut extends BaseClass{
	
	@Test
	public void testPut(){
		
		String id = (int)(1000*(Math.random()))+"";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Dell");
		bag.setId(id);
		bag.setLaptopName("Latitude");
		Features feat = new Features();
	    feat.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
	    bag.setFeatures(feat);
	    
	    given().log().body()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.when()
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	    
	    feat.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive","This is a Put method")));
	    bag.setFeatures(feat);
		
	    given()
	    .log().body()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.when()
		.body(bag)
		.post("/update")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Features.feature", hasItem("This is a Put method"));

	}

}
