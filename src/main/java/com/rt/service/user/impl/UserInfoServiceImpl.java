package com.rt.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.dao.UserInfoDao;
import com.rt.entity.model.UserInfo;
import com.rt.service.user.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	UserInfoDao userInfoDao;
	
	@Override
	public UserInfo findById(Integer uid) {
		return userInfoDao.findByUId(uid);
	}

}
