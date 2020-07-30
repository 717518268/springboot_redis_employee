package com.wang.dao;

import com.wang.pojo.User;

import java.util.List;

public interface UserMapper {
	
	/*
	 * 
	 */
	public User selectBy_numberid_one(Integer numberid);
	
    public User selectBynumberid(Integer numberid);

    public List<User>selectAllNumber();
    
    /**
     * 计算当前时间与入职时间的总天数
     * @param number
     * @return
     */
    public Integer selectByRegistertime_NowTime(Integer number);
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User selectIdForUser(String id);
    /**
     * 只查询出所有编号
     * @param did
     * @return
     */
    public List<User>selectByDidAll(Integer did);
    
    /**
     * 获取所有年份
     * @return
     */
    public List<User>selectAll_registertime();
    
    public List<User>SelectAllDidDepartment(Integer did);
    
    /**
     * 根据名字查询一对一
     * @param username
     * @return
     */
    public User selectBy_Name(String username);
    
    public Integer deleteByPrimaryKey(String id);
    public Integer insertSelective(User user);
    
    public Integer updateByPrimaryKey(User user);
    
    public Integer updaterByAllid(User user);
    
    
    public Integer updateBynumbers(User user);
    
    public Integer updateByName(User user);
    
    public Integer updateRegitserTime(User user);
    
    public Integer updatedepartment(User user);
    
}
