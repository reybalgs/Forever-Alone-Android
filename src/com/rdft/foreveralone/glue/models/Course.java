package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Course extends DatastoreEntity {
	String courseCode;
	String description;
	University university;
	
	public Course(JSONObject jObj) throws JSONException {
		super(jObj);
		courseCode = jObj.getString("courseCode");
		description = jObj.getString("description");
	}
}
