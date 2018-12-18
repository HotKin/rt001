//package com.rt.commons.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.rt.commons.interceptor.MyInterceptor;
//
//@SpringBootConfiguration
//public class MyAdapter implements WebMvcConfigurer{
//	
//	@Autowired
//	MyInterceptor myInterceptor;
//	
//	public void addInterceptors(InterceptorRegistry reg) {
//		reg.addInterceptor(myInterceptor).addPathPatterns("/**");
//	}
//}
