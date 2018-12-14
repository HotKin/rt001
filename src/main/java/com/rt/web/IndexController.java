package com.rt.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.rt.commons.aop.annotation.B;
import com.rt.core.RT;
import com.rt.core.RtController;
import com.rt.entity.model.UserInfo;
import com.rt.service.user.UserInfoService;

@Controller
@RequestMapping("/")
public class IndexController extends RtController {
	public final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	UserInfoService userInfoService;
	
	@B(value=IndexController.class)
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		return "hello world.";
	}
	
	@RequestMapping(value="/byId",method=RequestMethod.POST)
	public void findById(HttpServletResponse response) {
		PrintWriter out;
		response.setCharacterEncoding(RT.me().getConstants().getEncoding());
		if(isMoblieBrowser()) {
			UserInfo userInfo=userInfoService.findById(1);
			try {
				out = response.getWriter();
				response.setContentType("application/json; charset=utf-8");
				out.print(JSON.toJSONString(userInfo));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			renderText("普通浏览器");
		}
	}
}
