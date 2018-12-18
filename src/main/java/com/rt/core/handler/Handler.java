package com.rt.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {
	
	protected Handler next;
	
	
	public abstract void handle(String target, HttpServletRequest request, HttpServletResponse response);
}
