package com.rdft.foreveralone.glue.models;

public class Friend extends DatastoreEntity {
	public String name;
	public UserProfile profile;
	
	public String getAPIPath() {
		return "/api/friend";
	}
	
	@Override
	public boolean isSendable() {
		return true;
	}
}
