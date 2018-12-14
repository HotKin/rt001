package com.rt.core.render;

import javax.servlet.ServletContext;

import com.rt.core.Constants;


public class RenderManager {
	private Constants constants;
	private ServletContext servletContext;
	private IRenderFactory renderFactory = null;
	
	private static final RenderManager me = new RenderManager();
	private RenderManager() {}
	
	public static RenderManager me() {
		return me;
	}
	
	public IRenderFactory getRenderFactory() {
		return renderFactory;
	}
	
	public void init() {
		init(constants, servletContext);
	}
	
	public void init(Constants constants, ServletContext servletContext) {
		this.constants=constants;
		this.servletContext=servletContext;
		if(renderFactory == null) {
			renderFactory=new RenderFactory();
		}
		renderFactory.init(constants, servletContext);
	}

	public Constants getConstants() {
		return constants;
	}

	public void setConstants(Constants constants) {
		this.constants = constants;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
