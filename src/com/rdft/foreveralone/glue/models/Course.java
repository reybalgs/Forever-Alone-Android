package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Course extends DatastoreEntity {
	public String courseCode;
	public String description;
	public University university;

	public Course() {
		courseCode = "ADVATF2";
		description = "Advanced Team Fortress 2. Bring hats.";
	}

	public Course(JSONObject jObj) throws JSONException {
		super(jObj);
		courseCode = jObj.getString("courseCode");
		description = jObj.getString("description");
	}

	@Override
	public String getAPIPath() {
		return "/api/course";
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("courseCode", courseCode);
		jObj.put("description", description);

		jObj.put("universityKey", university.getEntityKey());

		return jObj;
	}

	@Override
	public boolean isSendable() {
		if ((university == null) || (courseCode == null)
				|| (description == null))
			return false;
		return true;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getDescription() {
		return description;
	}
}
