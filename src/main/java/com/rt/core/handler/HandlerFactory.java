package com.rt.core.handler;

import java.util.List;

public class HandlerFactory {
	
	private HandlerFactory() {
		
	}
	
	public static Handler getHandler(List<Handler> handlerList, Handler actionHandler) {
		Handler result = actionHandler;
		
		for (int i=handlerList.size()-1; i>=0; i--) {
			Handler temp = handlerList.get(i);
			temp.next = result;
			result = temp;
		}
		
		return result;
	}
}




