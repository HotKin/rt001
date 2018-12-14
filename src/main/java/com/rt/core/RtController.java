package com.rt.core;

import com.rt.commons.utils.RequestUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RtController extends Controller {
	

    /**
     * 是否是手机浏览器
     *
     * @return
     */
    public boolean isMoblieBrowser() {
        return RequestUtils.isMoblieBrowser(getRequest());
    }

    /**
     * 是否是微信浏览器
     *
     * @return
     */
    public boolean isWechatBrowser() {
        return RequestUtils.isWechatBrowser(getRequest());
    }

    /**
     * 是否是IE浏览器
     *
     * @return
     */
    public boolean isIEBrowser() {
        return RequestUtils.isIEBrowser(getRequest());
    }

    /**
     * 是否是ajax请求
     *
     * @return
     */
    public boolean isAjaxRequest() {
        return RequestUtils.isAjaxRequest(getRequest());
    }

    /**
     * 是否是multpart的请求（带有文件上传的请求）
     *
     * @return
     */
    public boolean isMultipartRequest() {
        return RequestUtils.isMultipartRequest(getRequest());
    }


    /**
     * 获取ip地址
     *
     * @return
     */
    public String getIPAddress() {
        return RequestUtils.getIpAddress(getRequest());
    }

    /**
     * 获取 referer
     *
     * @return
     */
    public String getReferer() {
        return RequestUtils.getReferer(getRequest());
    }


    /**
     * 获取ua
     *
     * @return
     */
    public String getUserAgent() {
        return RequestUtils.getUserAgent(getRequest());
    }


    protected HashMap<String, Object> flash;

    public Controller setFlashAttr(String name, Object value) {
        if (flash == null) {
            flash = new HashMap<>();
        }

        flash.put(name, value);
        return this;
    }


    public Controller setFlashMap(Map map) {
        if (map == null) {
            throw new NullPointerException("map is null");
        }
        if (flash == null) {
            flash = new HashMap<>();
        }

        flash.putAll(map);
        return this;
    }


    public <T> T getFlashAttr(String name) {
        return flash == null ? null : (T) flash.get(name);
    }


    public HashMap<String, Object> getFlashAttrs() {
        return flash;
    }


    private HashMap<String, Object> jwtMap;

    public Controller setJwtAttr(String name, Object value) {
        if (jwtMap == null) {
            jwtMap = new HashMap<>();
        }

        jwtMap.put(name, value);
        return this;
    }


    public Controller setJwtMap(Map map) {
        if (map == null) {
            throw new NullPointerException("map is null, u show invoke setJwtAttr() before. ");
        }
        if (jwtMap == null) {
            jwtMap = new HashMap<>();
        }

        jwtMap.putAll(map);
        return this;
    }


    public <T> T getJwtAttr(String name) {
        return jwtMap == null ? null : (T) jwtMap.get(name);
    }


    public HashMap<String, Object> getJwtAttrs() {
        return jwtMap;
    }

    /**
     * 获取当前网址
     *
     * @return
     */
    public String getBaseUrl() {
        HttpServletRequest req = getRequest();
        int port = req.getServerPort();

        return port == 80
                ? String.format("%s://%s%s", req.getScheme(), req.getServerName(), req.getContextPath())
                : String.format("%s://%s%s%s", req.getScheme(), req.getServerName(), ":" + port, req.getContextPath());

    }
}
