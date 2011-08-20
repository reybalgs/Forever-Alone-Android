package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;

public class UserProfile extends DatastoreEntity {
	public String email;
	public String nickname;
	public University university;
	public Schedule currentSchedule;
	
	public int defaultClassLength;
	public boolean autoCalculateEndTime;

	public UserProfile(JSONObject jObj) throws JSONException {
		super(jObj);
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
		
		defaultClassLength = jObj.getInt("defaultClassLength");
		autoCalculateEndTime = jObj.getBoolean("autoCalculateEndTime");
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		if (university != null) {
			jObj.put("universityKey", university.getEntityKey());
		}
		
		jObj.put("nickname", nickname);
		
		if (currentSchedule != null) {
			jObj.put("currentScheduleKey", currentSchedule.getEntityKey());
		}
		
		jObj.put("defaultClassLength", defaultClassLength);
		jObj.put("autoCalculateEndTime", autoCalculateEndTime);
		
		DebugConfig.logInfo("UserProfile", "Hurr.");

		return jObj;
	}
}
