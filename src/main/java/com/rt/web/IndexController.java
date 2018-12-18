package com.rt.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.rt.core.RtController;
import com.rt.entity.model.UserInfo;
import com.rt.service.user.UserInfoService;

@RestController
@RequestMapping("/")
public class IndexController extends RtController {
	public final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index() {
		LOGGER.info("","","","");
		renderText("hello spring boot.");
	}
	
	@RequestMapping(value="/byId",method=RequestMethod.POST)
	public void findById() {
		if(isMoblieBrowser()) {
			UserInfo userInfo=userInfoService.findById(1);
			renderJson(JSON.toJSONString(userInfo));
		}else {
			renderText("普通浏览器");
		}
	}
}
