package com.rdft.foreveralone.glue.models;

import org.json.JSONObject;

public class Friend extends DatastoreEntity {
	public String name;
	public UserProfile profile;
	public Section[] commonSections;
	
	public Friend(JSONObject jObj) {
		
	}
	
	public String getAPIPath() {
		return "/api/friend";
	}
	
	@Override
	public boolean isSendable() {
		return true;
	}
}
