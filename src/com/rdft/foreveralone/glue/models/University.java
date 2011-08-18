package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class University extends DatastoreEntity {
	public String name;
	public String address;
	public int termsPerYear;
	
	public University() {
		name = "Herp Derp University";
		address = "42 Wallaby Way, Sydney, Australia";
		termsPerYear = 3;
	}
	
	public University(JSONObject jObj) throws JSONException {
		super(jObj);
		name = jObj.getString("name");
		address = jObj.getString("address");
		termsPerYear = jObj.getInt("termsPerYear");
	}
}
