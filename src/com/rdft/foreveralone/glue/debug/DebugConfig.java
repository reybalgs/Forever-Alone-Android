package com.rdft.foreveralone.glue.debug;

import android.util.Log;

public class DebugConfig {
	public static String address = "192.168.1.114:8080";
	// public static String address = "10.0.2.2:8080"; // For the emulator
	public static String TAG = "ForeverAlone";
	
	public static String getURL(String path) {
		return "http://" + address + path;
	}
	
	public static void logError(String tag, String message) {
		Log.e(TAG + "-" + tag, message);
	}
	
	public static void logInfo(String tag, String message) {
		Log.i(TAG + "-" + tag, message);
	}
}
