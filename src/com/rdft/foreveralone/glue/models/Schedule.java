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
		super(jObj);
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
	
	public void addClass(Section section) {
		classes.add(section);
	}
	
	protected JSONArray getSectionsJSON() throws JSONException {
		JSONArray jArray = new JSONArray();
		for (Section section : classes) {
			jArray.put(section.toJSONObject());
		}
		
		return jArray;
	}
	
	public String getAPIPath() {
		return "/api/schedule";
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("term", term);
		jObj.put("year", year);
		jObj.put("classes", getSectionsJSON());
		
		return jObj;
	}
}
