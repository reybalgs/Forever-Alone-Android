package com.rdft.foreveralone.glue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;

import android.util.Log;

public class JSONParser {
	private static String TAG = "JSONParser";
	
	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 * 
		 * (c) public domain:
		 * http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/
		 * 11/a-simple-restful-client-at-android/
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	private static String getResultString(HttpResponse response)
			throws IllegalStateException, IOException {
		HttpEntity entity = response.getEntity();
		InputStream instream;
		String resultString = null;
		Header contentEncoding = response.getFirstHeader("Content-Encoding");
		instream = entity.getContent();

		if (contentEncoding != null
				&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			instream = new GZIPInputStream(instream);
		}

		// convert content stream to a String
		resultString = convertStreamToString(instream);
		instream.close();

		return resultString;
	}

	public static JSONObject getObject(HttpResponse response)
			throws JSONException, IOException {
		String resultString = getResultString(response);
		DebugConfig.logInfo(TAG, "Received JSON: " + resultString);

		// Transform the String into a JSONObject
		JSONObject jsonObjRecv = new JSONObject(resultString);

		// Raw DEBUG output of our received JSON object:
		DebugConfig.logInfo(TAG, "<jsonobject>\n" + jsonObjRecv.toString()
				+ "\n</jsonobject>");

		return jsonObjRecv;
	}

	public static JSONArray getArray(HttpResponse response)
			throws JSONException, IllegalStateException, IOException {
		String json = getResultString(response);
		DebugConfig.logInfo(TAG, "Received JSON: " + json);
		JSONArray jsonArray = new JSONArray(json);
		DebugConfig.logInfo(TAG, "<jsonobject>\n" + jsonArray.toString()
				+ "\n</jsonobject>");
		return jsonArray;
	}
}
