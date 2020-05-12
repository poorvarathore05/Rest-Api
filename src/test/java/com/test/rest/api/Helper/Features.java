package com.test.rest.api.Helper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Features {
	
	private List<String> feature;

	public Features(){
		feature = new ArrayList<String>();
		
	}
	@XmlElement(name ="Feature")
	public List<String> getFeature() {
		return feature;
	}

	public void setFeature(List<String> feature) {
		this.feature = feature;
	}
}
