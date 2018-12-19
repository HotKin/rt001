package com.rt.commons.aop.aspect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.date.DateUtil;

@Aspect
@Configuration
public class ActionRequestAspect {
	
	private static int maxOutputLengthOfParaValue = 512;
	
	private static Writer writer = new SystemOutWriter();
	
	public static void setWriter(Writer writer) {
		if (writer == null) {
			throw new IllegalArgumentException("writer can not be null");
		}
		ActionRequestAspect.writer = writer;
	}

    @Pointcut("execution(* com.rt.web.*Controller.*(..))")
    public void action() {
    }
    
    @Before("execution(* com.rt.web.*Controller.*(..))")
	public void before(JoinPoint pjp) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String Controller=pjp.getSignature().getDeclaringTypeName();
        String mt=pjp.getSignature().getName();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        
        String contentType=request.getContentType();
        
        
        StringBuilder sb = new StringBuilder();
        sb
        .append("Action report -------- ").append(DateUtil.now()).append(" --------------------------\n")
        .append("RequestURL  : ").append(url).append("\n")
        .append("Url         : ").append(method).append(" ").append(uri).append("\n");
		Enumeration<String> e = request.getParameterNames();
		if (e.hasMoreElements()) {
			sb.append("Parameter   : ");
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					sb.append(name).append("=");
					if (values[0] != null && values[0].length() > maxOutputLengthOfParaValue) {
						sb.append(values[0].substring(0, maxOutputLengthOfParaValue)).append("...");
					} else {
						sb.append(values[0]);
					}
				}
				else {
					sb.append(name).append("[]={");
					for (int i=0; i<values.length; i++) {
						if (i > 0)
							sb.append(",");
						sb.append(values[i]);
					}
					sb.append("}");
				}
				sb.append("  ");
			}
			sb.append("\n");
		}else {
			switch (method) {
			case "GET":
				Map<String, String> paramMaps=getParameterStringMap(request);
				sb.append("Parameter   : ");
				paramMaps.forEach((k,v)->{
					sb.append(k).append("=").append(v).append("\t");
				});
				sb.append("\n");
				break;
			case "POST":
				Object paramJson = getJson(request);
				sb.append("Parameter   : ").append(paramJson).append("\n");
				break;
			default:
				break;
			}
		}
		sb
		.append("Controller  : ").append(Controller).append("\n")
		.append("Method      : ").append(mt).append("\n")
		.append("---------------------------------------------------------------------\n");
		try {
			writer.write(sb.toString());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
    
/*	@Around("execution(* com.rt.web.*Controller.*(..))")
	public String around(ProceedingJoinPoint pjp) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String Controller=pjp.getSignature().getDeclaringTypeName();
        String mt=pjp.getSignature().getName();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        
        String contentType=request.getContentType();
        
        
        StringBuilder sb = new StringBuilder();
        sb
        .append("Action report -------- ").append(DateUtil.now()).append(" --------------------------\n")
        .append("RequestURL  : ").append(url).append("\n")
        .append("Url         : ").append(method).append(" ").append(uri).append("\n");
		Enumeration<String> e = request.getParameterNames();
		if (e.hasMoreElements()) {
			sb.append("Parameter   : ");
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					sb.append(name).append("=");
					if (values[0] != null && values[0].length() > maxOutputLengthOfParaValue) {
						sb.append(values[0].substring(0, maxOutputLengthOfParaValue)).append("...");
					} else {
						sb.append(values[0]);
					}
				}
				else {
					sb.append(name).append("[]={");
					for (int i=0; i<values.length; i++) {
						if (i > 0)
							sb.append(",");
						sb.append(values[i]);
					}
					sb.append("}");
				}
				sb.append("  ");
			}
			sb.append("\n");
		}else {
			switch (method) {
			case "GET":
				Map<String, String> paramMaps=getParameterStringMap(request);
				sb.append("Parameter   : ");
				paramMaps.forEach((k,v)->{
					sb.append(k).append("=").append(v).append("\t");
				});
				sb.append("\n");
				break;
			case "POST":
				Object paramJson = getJson(request);
				sb.append("Parameter   : ").append(paramJson).append("\n");
				break;
			default:
				break;
			}
		}
		sb
		.append("Controller  : ").append(Controller).append("\n")
		.append("Method      : ").append(mt).append("\n");
		String result=null;
		try {
			result=(String) pjp.proceed();
			sb.append("Result      : ").append(result).append("\n");
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sb.append("---------------------------------------------------------------------\n");
		try {
			writer.write(sb.toString());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return result;
	}*/
	
	public Object getJson(HttpServletRequest request) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isJSON=isJsonValid(json);
        if(!isJSON) {
        	return json;
        }else {
        	JSONObject jsonObject=JSONObject.parseObject(json);
        	return jsonObject;
        }
    }
	
	public boolean isJsonValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
	
	private Map<String, String> getParameterStringMap(HttpServletRequest request) {
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
	
	private static class SystemOutWriter extends Writer {
		public void write(String str) throws IOException {
			System.out.print(str);
		}
		public void write(char[] cbuf, int off, int len) throws IOException {}
		public void flush() throws IOException {}
		public void close() throws IOException {}
	}
}