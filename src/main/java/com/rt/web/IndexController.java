package com.rt.web;

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
	public String index() {
		LOGGER.info("","","","");
		//renderText("hello spring boot.");
		return "123456";
	}
	
	@RequestMapping(value="/byId",method=RequestMethod.POST)
	public String findById() {
		if(isMoblieBrowser()) {
			UserInfo userInfo=userInfoService.findById(1);
			return JSON.toJSONString(userInfo);
		}else {
			return "普通浏览器";
		}
	}
}
