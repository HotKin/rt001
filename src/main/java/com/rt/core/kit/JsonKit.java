package com.rt.core.kit;

import com.rt.core.Json;

/**
 * JsonKit.
 */
public class JsonKit {
	
	public static String toJson(Object object) {
		return Json.getJson().toJson(object);
	}
	
	public static <T> T parse(String jsonString, Class<T> type) {
		return Json.getJson().parse(jsonString, type);
	}
}

