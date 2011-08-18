package com.rdft.foreveralone.glue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.rdft.foreveralone.glue.debug.DebugConfig;
import com.rdft.foreveralone.glue.models.University;

public class CommHandler {
	HttpClient client;

	public CommHandler() {
		client = new DefaultHttpClient();
	}

	public CommHandler(HttpClient customClient) {
		client = customClient;
	}

	private JSONObject toJSON() {
		return null;
	}

	private void post(String url) {
		HttpPost request;
		try {
			request = new HttpPost(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private JSONArray getArray(String url) {
		JSONArray jsonArray = null;
		try {
			HttpGet request = new HttpGet(new URI(url));
			HttpResponse response = client.execute(request);
			jsonArray = JSONParser.getArray(response);
		} catch (IOException e) {
			Log.e("FA_NETCODE", "I/O error in HTTP GET");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public University[] getUniversities() {
		JSONArray jsonArray = getArray(DebugConfig.getURL("/api/university"));
		if (jsonArray == null) {
			Log.e("FA_NETCODE", "Failed to retrieve universities!");
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
}
