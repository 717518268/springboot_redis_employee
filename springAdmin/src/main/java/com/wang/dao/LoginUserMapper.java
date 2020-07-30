package com.wang.dao;

import com.wang.pojo.LoginUser;

 
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginUserMapper {
	
	
	/***
	 * 登录
	 * @param loginUser
	 * @return
	 */
	public LoginUser selectByLogin(LoginUser loginUser);
	
	public Integer deleteByPrimaryKey(String lid);
	
	public Integer insert(LoginUser loginUser);
	
	public Integer insertSelective(LoginUser loginUser);
	
	public Integer updateByCount(LoginUser loginUser);
    
}