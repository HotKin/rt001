package com.rt.core;
/**
 * Global constants definition
 */
public interface Const {
	
	String JFINAL_VERSION = "3.6";
	
	String DEFAULT_BASE_UPLOAD_PATH = "upload";
	
	String DEFAULT_BASE_DOWNLOAD_PATH = "download";
	
	String DEFAULT_ENCODING = "UTF-8";
	
	boolean DEFAULT_DEV_MODE = false;
	
	String DEFAULT_URL_PARA_SEPARATOR = "-";
	
	String DEFAULT_VIEW_EXTENSION = ".html";
	
	int DEFAULT_MAX_POST_SIZE = 1024 * 1024 * 10;  			// Default max post size of multipart request: 10 Meg
	
	int DEFAULT_I18N_MAX_AGE_OF_COOKIE = 999999999;
	
	int DEFAULT_FREEMARKER_TEMPLATE_UPDATE_DELAY = 3600;	// For not devMode only
	
	String DEFAULT_TOKEN_NAME = "_jfinal_token";
	
	int DEFAULT_SECONDS_OF_TOKEN_TIME_OUT = 900;			// 900 seconds ---> 15 minutes
	
	int MIN_SECONDS_OF_TOKEN_TIME_OUT = 300;				// 300 seconds ---> 5 minutes
	
	int DEFAULT_CONFIG_PLUGIN_ORDER = 2;
	
	boolean DEFAULT_INJECT_DEPENDENCY = false;
}

