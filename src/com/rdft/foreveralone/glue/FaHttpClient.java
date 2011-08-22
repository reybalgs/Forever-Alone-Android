package com.rdft.foreveralone.glue;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;

public class FaHttpClient extends DefaultHttpClient {
	String TAG = "FaHttpClient";
	public void post(String url) {
		try {
			HttpPost request = new HttpPost(new URI(url));
			this.execute(request);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP POST");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String postJSON(String url, JSONObject jObj) {
		try {
			DebugConfig.logInfo(TAG, "POSTing JSON to " + url);
			HttpPost request = new HttpPost(DebugConfig.getURL(url));

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("json", jObj.toString()));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = this.execute(request);
			HttpEntity resEntity = response.getEntity();
			String entityKey = EntityUtils.toString(resEntity);
			if (resEntity != null) {
				DebugConfig.logInfo(TAG, "This should be an entity key: " + entityKey);
				return entityKey;
			}
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP POST");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void putJSON(String url, JSONObject jObj) {
		try {
			DebugConfig.logInfo(TAG, "PUTting JSON to " + url);
			HttpPut request = new HttpPut(DebugConfig.getURL(url));

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("json", jObj.toString()));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			this.execute(request);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP POST");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject getObject(String url) {
		JSONObject jsonObj = null;
		try {
			long start, end;
			start = System.currentTimeMillis();
			HttpGet request = new HttpGet(new URI(url));
			HttpResponse response = this.execute(request);
			end = System.currentTimeMillis();
			DebugConfig
					.logInfo(TAG, "HTTP GET took [" + (end - start) + "] ms");
			jsonObj = JSONReader.getObject(response);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP GET");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	public JSONArray getArray(String url) {
		JSONArray jsonArray = null;
		try {
			long start, end;
			start = System.currentTimeMillis();
			HttpGet request = new HttpGet(new URI(url));
			HttpResponse response = this.execute(request);
			end = System.currentTimeMillis();
			DebugConfig
					.logInfo(TAG, "HTTP GET took [" + (end - start) + "] ms");
			jsonArray = JSONReader.getArray(response);
		} catch (IOException e) {
			DebugConfig.logError(TAG, "I/O error in HTTP GET");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
