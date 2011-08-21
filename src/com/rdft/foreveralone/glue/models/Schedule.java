package com.rdft.foreveralone.glue.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Schedule extends DatastoreEntity {
	public int term;
	public int year;
	public ArrayList<Section> classes;
	
	public Schedule(JSONObject jObj) throws JSONException {
		term = jObj.getInt("term");
		year = jObj.getInt("year");
		
		classes = new ArrayList<Section>();
		JSONArray classesJson = jObj.getJSONArray("classes");
		for (int i = 0; i < classesJson.length(); i++) {
			JSONObject jClass = classesJson.getJSONObject(i);
			Section section = new Section(jClass);
			classes.add(section);
		}
	}
}
