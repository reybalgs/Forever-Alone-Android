package com.rdft.foreveralone.glue.models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Meeting extends DatastoreEntity {
	Section section;
	
	boolean isSpecificDay;
	Date specificDate;

	int days;
	Date startTime;
	Date endTime;
	
	public Meeting(JSONObject jObj) throws JSONException {
		super(jObj);
		
		isSpecificDay = jObj.getBoolean("isSpecificDay");
		// specificDate = (Date) jObj.get("specificDate");
		
		days = jObj.getInt("days");
	}
	
	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("section", section);
		
		jObj.put("isSpecificDay", isSpecificDay);
		jObj.put("specificDate", specificDate);
		jObj.put("days", days);
		
		jObj.put("startTime", startTime);
		jObj.put("endTime", endTime);
		
		return jObj;
	}
}
