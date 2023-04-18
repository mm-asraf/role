package com.roleservice.roles.entity.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static final  Gson customGson = new GsonBuilder().create();
	
	public static String toJson(Object obj) {
		return customGson.toJson(obj);
		
	}
	
	public static <T> T fromJson(String json,Class<T> classOfT) {
		return customGson.fromJson(json, classOfT);
	}
}
