package com.roleservice.roles.utils;

import org.springframework.stereotype.Service;

@Service
public class StringUtilities {
	
	public static String buildString(String... params) {
		if (params != null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < params.length; i++) {
				sb.append(params[i]);
			}
			
			return sb.toString();
		}
		return "";
	}
	
}
