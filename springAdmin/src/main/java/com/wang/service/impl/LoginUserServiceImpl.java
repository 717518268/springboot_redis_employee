package com.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.dao.LoginUserMapper;
import com.wang.pojo.LoginUser;
import com.wang.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService{
	
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Override
	public LoginUser selectByLogin(LoginUser loginUser) {
		// TODO Auto-generated method stub
		return loginUserMapper.selectByLogin(loginUser);
	}

	@Override
	public Integer deleteByPrimaryKey(String  lid) {
		// TODO Auto-generated method stub
		return loginUserMapper.deleteByPrimaryKey(lid);
	}

	@Override
	public Integer insert(LoginUser loginUser) {
		// TODO Auto-generated method stub
		return loginUserMapper.insert(loginUser);
	}

	@Override
	public Integer insertSelective(LoginUser loginUser) {
		// TODO Auto-generated method stub
		return loginUserMapper.insertSelective(loginUser);
	}

	@Override
	public Integer updateByCount(LoginUser loginUser) {
		// TODO Auto-generated method stub
		return loginUserMapper.updateByCount(loginUser);
	}

}
