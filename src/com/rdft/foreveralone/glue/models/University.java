package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class University extends DatastoreEntity {
	public String name;
	public String address;
	public int termsPerYear;
	
	public University(JSONObject jObj) throws JSONException {
		name = jObj.getString("name");
		address = jObj.getString("address");
		termsPerYear = jObj.getInt("termsPerYear");
	}
}
