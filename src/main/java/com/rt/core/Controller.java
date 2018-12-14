package com.rt.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rt.core.kit.JsonKit;

public abstract class Controller{
	
	public HttpServletRequest request;
	public HttpServletResponse response;
	protected static String encoding = Const.DEFAULT_ENCODING;
	protected String jsonText;
	protected String[] attrs;
	protected static final String contentType = "application/json; charset=" + getEncoding();
	protected static final String contentTypeForIE = "text/html; charset=" + getEncoding();
	protected boolean forIE = false;
	
	
	public void init() {
		getRequest();
		getResponse();
	}
	
	/**
	 * Return HttpServletRequest. Do not use HttpServletRequest Object in constructor of Controller
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		request=servletRequestAttributes.getRequest();
		return request;
	}
	
	/**
	 * Return HttpServletResponse. Do not use HttpServletResponse Object in constructor of Controller
	 */
	public HttpServletResponse getResponse() {
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		response=servletRequestAttributes.getResponse();
		return response;
	}
	
	/**
	 * Return HttpSession.
	 */
	public HttpSession getSession() {
		return request.getSession();
	}
	
	/**
	 * Return HttpSession.
	 * @param create a boolean specifying create HttpSession if it not exists
	 */
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}
	
	/**
	 * Return a Object from session.
	 * @param key a String specifying the key of the Object stored in session
	 */
	public <T> T getSessionAttr(String key) {
		HttpSession session = request.getSession(false);
		return session != null ? (T)session.getAttribute(key) : null;
	}
	
	public <T> T getSessionAttr(String key, T defaultValue) {
		T result = getSessionAttr(key);
		return result != null ? result : defaultValue;
	}
	
	/**
	 * Store Object to session.
	 * @param key a String specifying the key of the Object stored in session
	 * @param value a Object specifying the value stored in session
	 */
	public Controller setSessionAttr(String key, Object value) {
		request.getSession(true).setAttribute(key, value);
		return this;
	}
	
	
	
	// TODO public <T> List<T> getModels(Class<T> modelClass, String modelName) {}
	
	// --------
	
	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder ret;
			br = request.getReader();
			
			String line = br.readLine();
			if (line != null) {
				ret = new StringBuilder();
				ret.append(line);
			} else {
				return "";
			}
			
			while ((line = br.readLine()) != null) {
				ret.append('\n').append(line);
			}
			
			return ret.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		/* 去掉 close() 否则后续 ActionReporter 中的 getPara() 在部分 tomcat 中会报 IOException : Stream closed
		finally {
			if (br != null) {
				try {br.close();} catch (IOException e) {LogKit.error(e.getMessage(), e);}
			}
		}*/
	}
	
	public void renderText(String text) {
		PrintWriter writer = null;
		try {
			getResponse();
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String DEFAULT_CONTENT_TYPE = "text/plain";
			response.setContentType(DEFAULT_CONTENT_TYPE);
			response.setCharacterEncoding(Const.DEFAULT_ENCODING);	// 与 contentType 分开设置
			
			writer = response.getWriter();
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	public void renderJson(String jsonText) {
		if (jsonText == null) {
			buildJsonText();
		}
		
		PrintWriter writer = null;
		try {
			getResponse();
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setContentType(forIE ? contentTypeForIE : contentType);
			writer = response.getWriter();
	        writer.write(jsonText);
	        writer.flush();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	protected void buildJsonText() {
		Map map = new HashMap();
		if (attrs != null) {
			for (String key : attrs) {
				map.put(key, request.getAttribute(key));
			}
		}
		else {
			for (Enumeration<String> attrs=request.getAttributeNames(); attrs.hasMoreElements();) {
				String key = attrs.nextElement();
				if (excludedAttrs.contains(key)) {
					continue;
				}
				
				Object value = request.getAttribute(key);
				map.put(key, value);
			}
		}
		
		this.jsonText = JsonKit.toJson(map);
	}


	public void setRequest() {
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		this.request = servletRequestAttributes.getRequest();
	}


	public void setResponse(HttpServletResponse response) {
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		this.response = servletRequestAttributes.getResponse();
	}
	
	protected static final Set<String> excludedAttrs = new HashSet<String>() {
		private static final long serialVersionUID = 9186138395157680676L;
		{
			add("javax.servlet.request.ssl_session");
			add("javax.servlet.request.ssl_session_id");
			add("javax.servlet.request.ssl_session_mgr");
			add("javax.servlet.request.key_size");
			add("javax.servlet.request.cipher_suite");
			add("_res");	// I18nInterceptor 中使用的 _res
		}
	};

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		Controller.encoding = encoding;
	}
}
