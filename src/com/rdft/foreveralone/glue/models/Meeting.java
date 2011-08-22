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

	public Meeting(JSONObject jObj) throws JSONException {
		super(jObj);
		days = jObj.getInt("days");

		JSONObject jStart = jObj.getJSONObject("startTime");
		startTime = jsonToDate(jStart);

		JSONObject jEnd = jObj.getJSONObject("endTime");
		endTime = jsonToDate(jEnd);
	}

	public String getAPIPath() {
		return "/api/meeting";
	}

	/**
	 * Forever Alone uses a bit-field to store the days in which a Meeting will
	 * be held. To determine whether this includes a particular day, use: <br>
	 * <br>
	 * <code>
	 * int n = meeting.getRawDays() & Day.FRI;<br>
	 * if (n != 0) {<br>
	 * 		// There's a meeting on Fridays<br>
	 * }<br>
	 * </code><br>
	 * 
	 * @return Days in which this meeting applies to, as a bit-field
	 */
	public int getRawDays() {
		return days;
	}

	private Date jsonToDate(JSONObject jTime) {
		Date time = new Date();

		try {
			time.setHours(jTime.getInt("hour"));
			time.setMinutes(jTime.getInt("minute"));
		} catch (JSONException e) {
			DebugConfig.logError("Meeting-TimeConversion",
					"Failed to convert JSON to time!");
			e.printStackTrace();
			time = null;
		}

		return time;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("entityKey", getEntityKey());
		jObj.put("section", section);

		jObj.put("isSpecificDay", isSpecificDay);
		jObj.put("specificDate", specificDate);
		jObj.put("days", days);

		jObj.put("startTime", startTime);
		jObj.put("endTime", endTime);

		return jObj;
	}

	@Override
	public boolean isSendable() {
		if ((startTime == null) || (endTime == null)) 
			return false;
		return true;
	}
}
