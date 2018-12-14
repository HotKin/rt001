package com.rt.core;

public class RtJsonFactory implements IJsonFactory {
	
	private static final RtJsonFactory me = new RtJsonFactory();
	
	public static RtJsonFactory me() {
		return me;
	}
	
	public Json getJson() {
		return new RtJson();
	}
}


