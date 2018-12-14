package com.rt.core.config;

import java.io.File;
import java.util.Properties;

import com.rt.core.Const;
import com.rt.core.Constants;
import com.rt.core.kit.Prop;

public abstract class AbstractConfig {
	
	public abstract void configConstant(Constants me);
	
	
	public void onStop() {}
	
	protected Prop prop = null;
	
	public Properties loadPropertyFile(String fileName) {
		return loadPropertyFile(fileName, Const.DEFAULT_ENCODING);
	}
	
	public Properties loadPropertyFile(String fileName, String encoding) {
		prop = new Prop(fileName, encoding);
		return prop.getProperties();
	}
	
	public Properties loadPropertyFile(File file) {
		return loadPropertyFile(file, Const.DEFAULT_ENCODING);
	}
	
	public Properties loadPropertyFile(File file, String encoding) {
		prop = new Prop(file, encoding);
		return prop.getProperties();
	}
	
	public void unloadPropertyFile() {
		this.prop = null;
	}
	
	private Prop getProp() {
		if (prop == null) {
			throw new IllegalStateException("Load propties file by invoking loadPropertyFile(String fileName) method first.");
		}
		return prop;
	}
	
	public String getProperty(String key) {
		return getProp().get(key);
	}
	
	public String getProperty(String key, String defaultValue) {
		return getProp().get(key, defaultValue);
	}
	
	public Integer getPropertyToInt(String key) {
		return getProp().getInt(key);
	}
	
	public Integer getPropertyToInt(String key, Integer defaultValue) {
		return getProp().getInt(key, defaultValue);
	}
	
	public Long getPropertyToLong(String key) {
		return getProp().getLong(key);
	}
	
	public Long getPropertyToLong(String key, Long defaultValue) {
		return getProp().getLong(key, defaultValue);
	}
	
	public Boolean getPropertyToBoolean(String key) {
		return getProp().getBoolean(key);
	}
	
	public Boolean getPropertyToBoolean(String key, Boolean defaultValue) {
		return getProp().getBoolean(key, defaultValue);
	}
}







