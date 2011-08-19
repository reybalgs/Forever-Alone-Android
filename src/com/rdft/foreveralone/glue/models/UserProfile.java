package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile extends DatastoreEntity {
	public String email;
	public String nickname;
	University university;
	Schedule currentSchedule;

	public UserProfile(JSONObject jObj) throws JSONException {
		JSONObject jUni, jSched;
		email = jObj.getString("email");
		nickname = jObj.getString("nickname");

		jUni = safeGet(jObj, "university");
		if (jUni != null) {
			university = new University(jUni);
		}

		jSched = safeGet(jObj, "currentSchedule");
		if (jSched != null)
			currentSchedule = new Schedule(jSched);
	}
}
