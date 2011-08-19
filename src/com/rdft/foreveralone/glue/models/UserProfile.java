package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile extends DatastoreEntity {
	public String email;
	public String nickname;
	University university;
	Schedule currentSchedule;
	
	public UserProfile(JSONObject jObj) throws JSONException {
		email = jObj.getString("email");
		nickname = jObj.getString("nickname");
		university = new University(jObj.getJSONObject("university"));
		currentSchedule = new Schedule(jObj.getJSONObject("currentSchedule"));
	}
}
