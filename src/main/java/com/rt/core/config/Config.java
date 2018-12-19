package com.rt.core.config;

import com.rt.core.Constants;

public class Config {
	
	private static final Constants constants = new Constants();
	
	private Config() {
	}
	
	static void configJFinal(AbstractConfig jfinalConfig) {
		jfinalConfig.configConstant(constants);
	}
	
	
	public static final Constants getConstants() {
		return constants;
	}
}
