package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Course extends DatastoreEntity {
	public String courseCode;
	public String description;
	public University university;
	
	public Course(JSONObject jObj) throws JSONException {
		super(jObj);
		courseCode = jObj.getString("courseCode");
		description = jObj.getString("description");
	}
	
	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("courseCode", courseCode);
		jObj.put("description", description);
		jObj.put("university", university);
		
		return jObj;
	}
	
	public String getCourseCode()
	{
		return courseCode;
	}
	
	public String getDescription()
	{
		return description;
	}
}
