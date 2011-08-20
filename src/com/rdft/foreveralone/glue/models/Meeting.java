package com.rdft.foreveralone.glue.models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;

public class Meeting extends DatastoreEntity {
	Section section;

	/*
	 * TODO: Implement tracking of one-time courses (like LASARE) This should
	 * only be done after the MP submission, as it is possibly a rarely used/not
	 * useful enough feature
	 */
	boolean isSpecificDay;
	Date specificDate;

	int days;
	Date startTime;
	Date endTime;

	private Date jsonToDate(JSONObject jTime) {
		Date time = new Date();

		try {
			time.setHours(jTime.getInt("hour"));
			time.setMinutes(jTime.getInt("minute"));
		} catch (JSONException e) {
			DebugConfig.logError("Meeting-TimeConversion", "Failed to convert JSON to time!");
			e.printStackTrace();
		}

		return time;
	}

	public Meeting(JSONObject jObj) throws JSONException {
		super(jObj);
		days = jObj.getInt("days");

		JSONObject jStart = jObj.getJSONObject("startTime");
		startTime = jsonToDate(jStart);
		
		JSONObject jEnd = jObj.getJSONObject("endTime");
		endTime = jsonToDate(jEnd);
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
