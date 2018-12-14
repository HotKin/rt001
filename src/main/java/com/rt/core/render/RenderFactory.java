package com.rt.core.render;

import javax.servlet.ServletContext;

import com.rt.core.Constants;

public class RenderFactory implements IRenderFactory {
	
	protected Constants constants;
	protected ServletContext servletContext;

	@Override
	public void init(Constants constants, ServletContext servletContext) {
		this.constants = constants;
		this.servletContext = servletContext;
	}

	@Override
	public Render getRender(String view) {
		return new TextRender(view);
	}

	@Override
	public Render getJsonRender(String jsonText) {
		return new JsonRender(jsonText);
	}

	@Override
	public Render getTextRender(String str) {
		return new TextRender(str);
	}
}
