package com.rdft.foreveralone.glue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;
import com.rdft.foreveralone.glue.models.Course;
import com.rdft.foreveralone.glue.models.DatastoreEntity;
import com.rdft.foreveralone.glue.models.Schedule;
import com.rdft.foreveralone.glue.models.Section;
import com.rdft.foreveralone.glue.models.University;
import com.rdft.foreveralone.glue.models.UserProfile;

public class CommHandler {
	private static String TAG = "CommHandler";
	FaHttpClient http;

	public CommHandler(FaHttpClient customClient) {
		DebugConfig.logInfo(TAG,
				"CommHandler initialized with custom HttpClient");
		http = customClient;
	}

	public Section[] getSections() {
		JSONArray jArray = http.getArray(DebugConfig.getURL("/api/section"));
		if (jArray == null) {
			DebugConfig.logError(TAG, "Failed to retrieve sections!");
			return null;
		}

		Section[] sections = null;
		try {
			sections = new Section[jArray.length()];
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jSection = jArray.getJSONObject(i);
				sections[i] = new Section(jSection);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return sections;
	}

	public Course[] getCourses() {
		JSONArray jArray = http.getArray(DebugConfig.getURL("/api/course"));
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
		JSONArray jsonArray = http.getArray(DebugConfig
				.getURL("/api/university"));
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
		JSONObject jObj = http.getObject(DebugConfig.getURL("/api/profile"));
		if (jObj == null) {
			DebugConfig.logError(TAG, "Failed to retrieve profile!");
			return null;
		}

		UserProfile profile;
		profile = new UserProfile(jObj);
		return profile;
	}

	public void createDefaultProfile() {
		http.post(DebugConfig.getURL("/api/profile"));
	}

	public Schedule getCurrentSchedule() throws JSONException {
		JSONObject jObj;
		jObj = http.getObject(DebugConfig.getURL("/api/schedule/current"));

		Schedule schedule = null;
		if (jObj != null) {
			schedule = new Schedule(jObj);
		} else {
			return null;
		}
		return schedule;
	}

	public void update(DatastoreEntity thing) throws JSONException {
		JSONObject jsonObj = thing.toJSONObject();
		String path = thing.getAPIPath();

		DebugConfig.logInfo(TAG, "SENDING JSON: " + jsonObj.toString());

		if (thing.getEntityKey() == null) {
			DebugConfig
					.logInfo(TAG,
							"NOTE: Object has no entity key");
		}
		http.postJSON(path, jsonObj);
	}

	public interface ISendable {
		public void onPut(CommHandler handler);

		public void onPost(CommHandler handler);

		public void onDelete(CommHandler handler);
	}
}
