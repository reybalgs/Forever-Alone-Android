package com.rdft.foreveralone.glue.debug;

public class DebugConfig {
	public static String address = "10.0.2.2:8080";
	
	public static String getURL(String path) {
		return "http://" + address + path;
	}
}
