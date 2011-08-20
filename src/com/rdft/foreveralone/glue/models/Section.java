package com.rdft.foreveralone.glue.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Section extends DatastoreEntity {
	public String name;
	public Course course;
	
	public ArrayList<Meeting> meetings;
	
	public Section(JSONObject jObj) throws JSONException {
		name = jObj.getString("name");
		JSONArray jMeets = jObj.getJSONArray("meetings");
		
		meetings = new ArrayList<Meeting>();
		for (int i = 0; i < jMeets.length(); i++) {
			JSONObject jMeeting = jMeets.getJSONObject(i);
			Meeting meeting = new Meeting(jMeeting);
			meetings.add(meeting);
		}
	}
}
