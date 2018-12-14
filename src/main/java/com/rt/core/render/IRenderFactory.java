package com.rt.core.render;

import javax.servlet.ServletContext;

import com.rt.core.Constants;


public interface IRenderFactory {
	public void init(Constants constants, ServletContext servletContext);
	public Render getRender(String view);
	public Render getJsonRender(String jsonText);
	public Render getTextRender(String str);
}
