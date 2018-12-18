package com.rt.core.log;

import com.rt.core.kit.LogKit;

/**
 * LogManager.
 */
public class LogManager {
	
	private static final LogManager me = new LogManager();
	
	private LogManager() {}
	
	public static LogManager me() {
		return me;
	}
	
	public void init() {
		Log.init();
	}
	
	public void setDefaultLogFactory(ILogFactory defaultLogFactory) {
		Log.setDefaultLogFactory(defaultLogFactory);
		LogKit.synchronizeLog();
	}
}


