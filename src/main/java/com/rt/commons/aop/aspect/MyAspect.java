package com.rt.commons.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	
	@Around("@annotation(com.rt.commons.aop.annotation.B)")
	public String setValue(ProceedingJoinPoint pjp) {
		String method=pjp.getSignature().getName();
		System.out.println("环绕通知，"+method);
		try {
			String res=(String) pjp.proceed();
			return "环绕通知处理后返回结果："+res;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "Around";
	}
	
	@Before("@annotation(com.rt.commons.aop.annotation.B)")
	public String before(JoinPoint pjp) {
		System.out.println("设置之前。。。");
		return "设置之前";
	}
	
	@After("@annotation(com.rt.commons.aop.annotation.B)")
	public String After(JoinPoint pjp) {
		System.out.println("设置之后。。。");
		return "设置之后";
	}
}
