package com.rdft.foreveralone.glue;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;
import com.rdft.foreveralone.glue.models.*;

public class CommHandler {
	private static String TAG = "CommHandler";
	HttpClient client;

	public CommHandler() {
		client = new DefaultHttpClient();
	}

	public CommHandler(HttpClient customClient) {
		DebugConfig.logInfo(TAG, "CommHandler initialized with custom HttpClient");
		client = customClient;
	}

	private void postJSON(String url, JSONObject jObj) {
		try {
			HttpPost request = new HttpPost(new URI(url));
			client.execute(request);
			
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP POST");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONArray getArray(String url) {
		JSONArray jsonArray = null;
		try {
			long start, end;
			start = System.currentTimeMillis();
			HttpGet request = new HttpGet(new URI(url));
			HttpResponse response = client.execute(request);
			end = System.currentTimeMillis();
			DebugConfig.logInfo(TAG, "HTTP GET took [" + (end - start) + "] ms");
			jsonArray = JSONParser.getArray(response);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP GET");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	public Course[] getCourses() {
		JSONArray jArray = getArray(DebugConfig.getURL("/api/course"));
		if (jArray == null) {
			DebugConfig.logError(TAG, "Failed to retrieve courses!");
			return null;
		}
		
		Course[] courses = null;
		try {
			courses = new Course[jArray.length()];
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jCourse = jArray.getJSONObject(i);
				courses[i] = new Course(jCourse);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return courses;
	}

	public University[] getUniversities() {
		JSONArray jsonArray = getArray(DebugConfig.getURL("/api/university"));
		if (jsonArray == null) {
			DebugConfig.logError(TAG, "Failed to retrieve universities!");
			return null;
		}
		University[] universities = null;
		try {
			universities = new University[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jUni = jsonArray.getJSONObject(i);
				universities[i] = new University(jUni);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return universities;
	}
	
	public void addUniversity(University university) throws JSONException {
		JSONObject jObj = university.toJSONObject();
		
	}
}
