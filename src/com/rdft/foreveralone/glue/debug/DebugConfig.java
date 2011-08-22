package com.rdft.foreveralone.glue.debug;

import android.util.Log;

public class DebugConfig {
	// public static String address = "192.168.1.51:8080"; // at ~/
	// public static String address = "192.168.1.114:8080"; // at BH
	public static String address = "10.0.2.2:8080"; // For the emulator
	public static String TAG = "ForeverAlone";
	
	/* Allow the login code to assume that we're connecting to the dev appserver,
	 * which doesn't require a real Google account.
	 */
	public static final boolean ALLOW_FAKE_ACCOUNTS = true;
	
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
