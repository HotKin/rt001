package com.rt.core.render;

import java.io.IOException;
import java.io.PrintWriter;

import com.rt.core.ContentType;

public class TextRender extends Render{

protected static final String DEFAULT_CONTENT_TYPE = "text/plain";
	
	protected String text;
	protected String contentType;
	
	public TextRender(String text) {
		this.text = text;
		this.contentType = DEFAULT_CONTENT_TYPE;
		render();
	}
	
	public TextRender(String text, String contentType) {
		this.text = text;
		this.contentType = contentType;
		render();
	}
	
	public TextRender(String text, ContentType contentType) {
		this.text = text;
		this.contentType = contentType.value();
		render();
	}
	
	public void render() {
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setContentType(contentType);
			response.setCharacterEncoding(getEncoding());	// 与 contentType 分开设置
			
			writer = response.getWriter();
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	public String getText() {
		return text;
	}
	
	public String getContentType() {
		return contentType;
	}

}
