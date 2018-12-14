package com.rt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rt.entity.model.UserInfo;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
	UserInfo findByUId(Integer uId);
}
