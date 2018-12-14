package com.rt.core;

import javax.servlet.ServletContext;
import com.rt.core.config.Config;

/**
 * JFinal
 */
public final class RT {
	
	private Constants constants;
	private ServletContext servletContext;
	private String contextPath = "";
	
	private static final RT me = new RT();
	
	private RT() {
	}
	
	public static RT me() {
		return me;
	}
	
	void init(ServletContext servletContext) {
		this.servletContext = servletContext;
		this.contextPath = servletContext.getContextPath();
		constants = Config.getConstants();
	}
	
	
	public Constants getConstants() {
		return Config.getConstants();
	}
	
	public String getContextPath() {
		return contextPath;
	}
	
	public ServletContext getServletContext() {
		return this.servletContext;
	}
}










