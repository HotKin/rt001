package com.rt.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * The constant for JFinal runtime.
 */
final public class Constants {
	
	private boolean devMode = Const.DEFAULT_DEV_MODE;
	
	private String baseUploadPath = Const.DEFAULT_BASE_UPLOAD_PATH;
	private String baseDownloadPath = Const.DEFAULT_BASE_DOWNLOAD_PATH;
	
	private String encoding = Const.DEFAULT_ENCODING;
	private String urlParaSeparator = Const.DEFAULT_URL_PARA_SEPARATOR;
	private String viewExtension = Const.DEFAULT_VIEW_EXTENSION;
	private int maxPostSize = Const.DEFAULT_MAX_POST_SIZE;
	private int freeMarkerTemplateUpdateDelay = Const.DEFAULT_FREEMARKER_TEMPLATE_UPDATE_DELAY;	// just for not devMode
	
	private int configPluginOrder = Const.DEFAULT_CONFIG_PLUGIN_ORDER;
	
	private boolean injectDependency = Const.DEFAULT_INJECT_DEPENDENCY;
	
	/**
	 * Set development mode.
	 * @param devMode the development mode
	 */
	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}
	
	public boolean getDevMode() {
		return devMode;
	}
	
	/**
	 * 配置 configPlugin(Plugins me) 在 JFinalConfig 中被调用的次序.
	 * 
	 * 取值 1、2、3、4、5 分别表示在 configConstant(..)、configRoute(..)、
	 * configEngine(..)、configInterceptor(..)、configHandler(...)
	 * 之后被调用
	 * 
	 * 默认值为 2，那么 configPlugin(..) 将在 configRoute(...) 调用之后被调用
	 * @param 取值只能是 1、2、3、4、5
	 */
	public void setConfigPluginOrder(int configPluginOrder) {
		if (configPluginOrder < 1 || configPluginOrder > 5) {
			throw new IllegalArgumentException("configPluginOrder 只能取值为：1、2、3、4、5");
		}
		this.configPluginOrder = configPluginOrder;
	}
	
	public int getConfigPluginOrder() {
		return configPluginOrder;
	}
	
	
	/**
	 * Set encoding. The default encoding is UTF-8.
	 * @param encoding the encoding
	 */
	public void setEncoding(String encoding) {
		if (StringUtils.isBlank(encoding)) {
			throw new IllegalArgumentException("encoding can not be blank.");
		}
		this.encoding = encoding;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	
	public String getUrlParaSeparator() {
		return urlParaSeparator;
	}
	
	/**
	 * Set urlPara separator. The default value is "-"
	 * @param urlParaSeparator the urlPara separator
	 */
	public void setUrlParaSeparator(String urlParaSeparator) {
		if (StringUtils.isBlank(urlParaSeparator) || urlParaSeparator.contains("/")) {
			throw new IllegalArgumentException("urlParaSepartor can not be blank and can not contains \"/\"");
		}
		this.urlParaSeparator = urlParaSeparator;
	}
	
	public String getViewExtension() {
		return viewExtension;
	}
	
	/**
	 * Set view extension for the IRenderFactory.getDefaultRender(...)
	 * The default value is ".html"
	 * 
	 * Example: ".html" or ".ftl"
	 * @param viewExtension the extension of the view, it must start with dot char "."
	 */
	public void setViewExtension(String viewExtension) {
		this.viewExtension = viewExtension.startsWith(".") ? viewExtension : "." + viewExtension;
	}
	
	/**
	 * Set error 404 view.
	 * @param error404View the error 404 view
	 */
	public void setError404View(String error404View) {
		errorViewMapping.put(404, error404View);
	}
	
	/**
	 * Set error 500 view.
	 * @param error500View the error 500 view
	 */
	public void setError500View(String error500View) {
		errorViewMapping.put(500, error500View);
	}
	
	/**
	 * Set error 401 view.
	 * @param error401View the error 401 view
	 */
	public void setError401View(String error401View) {
		errorViewMapping.put(401, error401View);
	}
	
	/**
	 * Set error 403 view.
	 * @param error403View the error 403 view
	 */
	public void setError403View(String error403View) {
		errorViewMapping.put(403, error403View);
	}
	
	private Map<Integer, String> errorViewMapping = new HashMap<Integer, String>();
	
	public void setErrorView(int errorCode, String errorView) {
		errorViewMapping.put(errorCode, errorView);
	}
	
	public String getErrorView(int errorCode) {
		return errorViewMapping.get(errorCode);
	}
	
	public String getBaseDownloadPath() {
		return baseDownloadPath;
	}
	
	/**
	 * Set file base download path for Controller.renderFile(...)
	 * 设置文件下载基础路径，当路径以 "/" 打头或是以 windows 磁盘盘符打头，
	 * 则将路径设置为绝对路径，否则路径将是以应用根路径为基础的相对路径
	 * <pre>
	 * 例如：
	 * 1：参数 "/var/www/download" 为绝对路径，下载文件存放在此路径之下
	 * 2：参数 "download" 为相对路径，下载文件存放在 PathKit.getWebRoot() + "/download" 路径之下
	 * </pre>
	 */
	public void setBaseDownloadPath(String baseDownloadPath) {
		if (StringUtils.isBlank(baseDownloadPath)) {
			throw new IllegalArgumentException("baseDownloadPath can not be blank.");
		}
		this.baseDownloadPath = baseDownloadPath;
	}
	
	/**
	 * Set file base upload path.
	 * 设置文件上传保存基础路径，当路径以 "/" 打头或是以 windows 磁盘盘符打头，
	 * 则将路径设置为绝对路径，否则路径将是以应用根路径为基础的相对路径
	 * <pre>
	 * 例如：
	 * 1：参数 "/var/www/upload" 为绝对路径，上传文件将保存到此路径之下
	 * 2：参数 "upload" 为相对路径，上传文件将保存到 PathKit.getWebRoot() + "/upload" 路径之下
	 * </pre>
	 */
	public void setBaseUploadPath(String baseUploadPath) {
		if (StringUtils.isBlank(baseUploadPath)) {
			throw new IllegalArgumentException("baseUploadPath can not be blank.");
		}
		this.baseUploadPath = baseUploadPath;
	}
	
	public String getBaseUploadPath() {
		return baseUploadPath;
	}
	
	public int getMaxPostSize() {
		return maxPostSize;
	}
	
	/**
	 * Set max size of http post. The upload file size depend on this value.
	 */
	public void setMaxPostSize(int maxPostSize) {
		this.maxPostSize = maxPostSize;
	}
	
	/**
	 * FreeMarker template update delay for not devMode.
	 */
	public void setFreeMarkerTemplateUpdateDelay(int delayInSeconds) {
		if (delayInSeconds < 0) {
			throw new IllegalArgumentException("template_update_delay must more than -1.");
		}
		this.freeMarkerTemplateUpdateDelay = delayInSeconds;
	}
	
	public int getFreeMarkerTemplateUpdateDelay() {
		return freeMarkerTemplateUpdateDelay;
	}
}





