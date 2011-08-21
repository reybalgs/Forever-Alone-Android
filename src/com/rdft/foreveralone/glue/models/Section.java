package com.rdft.foreveralone.glue.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Section extends DatastoreEntity {
	public String name;
	public Course course;

	public ArrayList<Meeting> meetings;
	
	public Section() {
		meetings = new ArrayList<Meeting>();
	}
	
	public Section(JSONObject jObj) throws JSONException {
		super(jObj);
		name = jObj.getString("name");
		JSONArray jMeets = jObj.getJSONArray("meetings");
		
		meetings = new ArrayList<Meeting>();
		for (int i = 0; i < jMeets.length(); i++) {
			JSONObject jMeeting = jMeets.getJSONObject(i);
			Meeting meeting = new Meeting(jMeeting);
			meetings.add(meeting);
		}
	}
	public String getAPIPath() {
		return "/api/section";
	}
	
	public void addMeeting(Meeting meeting) {
		meetings.add(meeting);
	}
	
	public Meeting[] getMeetings() {
		Meeting[] meetArray = new Meeting[meetings.size()];
		int index = 0;
		
		for (Meeting meeting : meetings) {
			meetArray[index] = meeting;
			index++;
		}
		
		return meetArray;
	}
	
	protected JSONArray getMeetingsJSON() throws JSONException {
		JSONArray jArray = new JSONArray();
		for (Meeting meeting : meetings) {
			jArray.put(meeting.toJSONObject());
		}
		
		return jArray;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("entityKey", getEntityKey());
		jObj.put("name", name);
		jObj.put("meetings", getMeetingsJSON());
		return jObj;
	}
}
