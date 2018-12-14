package com.rt.commons.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Describe:
 * 	自定义注解
 * @Author CAO KUN
 * @Date 2018-12-10 16:52:33
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface B {
	Class<?> value();
}