package com.luke.infra.utils;

import org.json.JSONObject;

public class PrettyPrint {
	public static void jsonPrettyPrint(String jsonString) {
		JSONObject json = new JSONObject(jsonString); // Convert text to object
		System.out.println(json.toString(4)); // Print it with specified indentation
	}
}
