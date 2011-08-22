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
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("entityKey", getEntityKey());
		jObj.put("name", name);
		jObj.put("address", address);
		jObj.put("termsPerYear", termsPerYear);
		
		return jObj;
	}
	
	public boolean isSendable() {
		if ((name == null) || (address == null) || (termsPerYear <= 0))
			return false;
		return true;
	}
	
	public String getAPIPath() {
		return "/api/university";
	}
}
