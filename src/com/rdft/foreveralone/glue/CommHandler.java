package com.rdft.foreveralone.glue;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;
import com.rdft.foreveralone.glue.models.Course;
import com.rdft.foreveralone.glue.models.Schedule;
import com.rdft.foreveralone.glue.models.University;
import com.rdft.foreveralone.glue.models.UserProfile;

public class CommHandler {
	private static String TAG = "CommHandler";
	HttpClient client;

	public CommHandler() {
		client = new DefaultHttpClient();
	}

	public CommHandler(HttpClient customClient) {
		DebugConfig.logInfo(TAG,
				"CommHandler initialized with custom HttpClient");
		client = customClient;
	}

	private void post(String url) {
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

	private void postJSON(String url, JSONObject jObj) {
		try {
			DebugConfig.logInfo(TAG, "POSTing JSON to " + url);
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("json", jObj.toString()));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			client.execute(request);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP POST");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JSONObject getObject(String url) {
		JSONObject jsonObj = null;
		try {
			long start, end;
			start = System.currentTimeMillis();
			HttpGet request = new HttpGet(new URI(url));
			HttpResponse response = client.execute(request);
			end = System.currentTimeMillis();
			DebugConfig
					.logInfo(TAG, "HTTP GET took [" + (end - start) + "] ms");
			jsonObj = JSONParser.getObject(response);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP GET");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	private JSONArray getArray(String url) {
		JSONArray jsonArray = null;
		try {
			long start, end;
			start = System.currentTimeMillis();
			HttpGet request = new HttpGet(new URI(url));
			HttpResponse response = client.execute(request);
			end = System.currentTimeMillis();
			DebugConfig
					.logInfo(TAG, "HTTP GET took [" + (end - start) + "] ms");
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

	public University[] getUniversities() throws JSONException {
		JSONArray jsonArray = getArray(DebugConfig.getURL("/api/university"));
		if (jsonArray == null) {
			DebugConfig.logError(TAG, "Failed to retrieve universities!");
			return null;
		}
		
		University[] universities = null;
		universities = new University[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jUni = jsonArray.getJSONObject(i);
			universities[i] = new University(jUni);
		}

		return universities;
	}

	public UserProfile getProfile() throws JSONException {
		JSONObject jObj = getObject(DebugConfig.getURL("/api/profile"));
		if (jObj == null) {
			DebugConfig.logError(TAG, "Failed to retrieve profile!");
			return null;
		}

		UserProfile profile;
		profile = new UserProfile(jObj);
		return profile;
	}

	public void createDefaultProfile() {
		post(DebugConfig.getURL("/api/profile"));
	}

	public void updateProfile(UserProfile profile) throws JSONException {
		JSONObject jProfile = profile.toJSONObject();
		postJSON(DebugConfig.getURL("/api/profile"), jProfile);
	}

	public Schedule getCurrentSchedule() throws JSONException {
		JSONObject jObj = getObject(DebugConfig.getURL("/api/schedule/current"));
		Schedule schedule = new Schedule(jObj);

		return schedule;
	}
}
