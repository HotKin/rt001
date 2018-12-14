package com.rt.commons.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.Get;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.rt.core.RequestMethod;
import com.yixing.common.BodyReaderHttpServletRequestWrapper;

@Component
public class MyInterceptor implements HandlerInterceptor {

	private HttpServletRequest request;

	private HttpServletResponse response;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
		// 只有返回true才会继续向下执行，返回false取消当前请求
		try {
			this.request = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.response = httpServletResponse;
		String requestMethod = request.getMethod();
		if (RequestMethod.isPG(requestMethod)) {
			switch (requestMethod) {
			case "GET":
				get();
				break;
			case "POST":
				post();
				break;
			default:
				break;
			}
		}
		return true;
	}
	
	public void get() {
		Map<String, String> paramMaps=getParameterStringMap();
		paramMaps.forEach((k,v)->{
			System.out.println("k="+k+",v="+v);
		});
	}
	
	public void post() {
		JSONObject paramJson = getJson((HttpServletRequest) request);
		System.out.println("POST 请求参数："+paramJson.toJSONString());
	}
	
	public JSONObject getJson(HttpServletRequest request) {
        String json = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            json = sb.toString();
            if(StringUtils.isEmpty(json)) {
            	json="{}";
            }
            System.out.println("json=" + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        try {
        	jsonObject=JSONObject.parseObject(json);
		} catch (ClassCastException e) {
			System.out.println("转换失败。。。");
		}
        return jsonObject;
    }
	
	private Map<String, Object> getParameterMap() {
        Map<String, String[]> properties = request.getParameterMap();//把请求参数封装到Map<String, String[]>中
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<Entry<String, String[]>> iter = properties.entrySet().iterator();
        String name = "";
        String value = "";
        while (iter.hasNext()) {
            Entry<String, String[]> entry = iter.next();
            name = entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();//用于请求参数中请求参数名唯一
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
	
    private Map<String, String> getParameterStringMap() {
        Map<String, String[]> properties = request.getParameterMap();//把请求参数封装到Map<String, String[]>中
        Map<String, String> returnMap = new HashMap<String, String>();
        String name = "";
        String value = "";
        for (Map.Entry<String, String[]> entry : properties.entrySet()) {
            name = entry.getKey();
            String[] values = entry.getValue();
            if (null == values) {
                value = "";
            } else if (values.length>1) {
                for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = values[0];//用于请求参数中请求参数名唯一
            }
            returnMap.put(name, value);
            
        }
        return returnMap;
    }

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("myinterc posthandler");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

		System.out.println("myinterc aftercompletion");
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
