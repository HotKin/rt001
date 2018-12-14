package com.rt.core;

import org.apache.commons.lang3.StringUtils;

public enum RequestMethod {
	GET("GET"), 
	HEAD("HEAD"), 
	POST("POST"), 
	PUT("PUT"), 
	PATCH("PATCH"), 
	DELETE("DELETE"), 
	OPTIONS("OPTIONS"), 
	TRACE("TRACE");
	
	RequestMethod(String method) {
		this.method=method;
	}
	
	private String method;
	
	public String getMethod() {
		return method;
	}

	public static boolean isPG(String me) {
		boolean flag=false;
		if(StringUtils.equalsAnyIgnoreCase(me, RequestMethod.POST.getMethod())
				||StringUtils.equalsAnyIgnoreCase(me, RequestMethod.GET.getMethod())) {
			flag=true;
		}
		return flag;
	}
	
	@Override
	public String toString() {
		return "method:"+method;
	}
	
}
