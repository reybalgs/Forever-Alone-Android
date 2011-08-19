package com.rdft.foreveralone.glue.models;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Schedule extends DatastoreEntity {
	String name;
	int term;
	int year;
	ArrayList<Section> classes;
	
	public Schedule(JSONObject jObj) throws JSONException {
		name = jObj.getString("name");
		term = jObj.getInt("term");
		year = jObj.getInt("year");

		/* TODO: get classes */
		// JSONArray classesJson = jObj.getJSONArray("classes");
	}
}
