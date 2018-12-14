package com.rt.core.render;

public enum ContentType {
	TEXT("text/plain"),
	HTML("text/html"),
	XML("text/xml"),
	JSON("application/json"),
	JAVASCRIPT("application/javascript");
	
	private final String value;
	
	private ContentType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	public String toString() {
		return value;
	}
}