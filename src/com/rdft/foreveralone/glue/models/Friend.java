package com.rdft.foreveralone.glue.models;

public class Friend extends DatastoreEntity {
	public String name;
	public int commonCourses;
	
	public String getAPIPath() {
		return "/api/friend";
	}
}
