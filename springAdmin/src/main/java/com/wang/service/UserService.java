package com.wang.service;

import com.wang.pojo.User;

import java.util.List;

public interface UserService {
	public User selectBy_numberid_one(Integer numberid);
    public User selectBynumberid(Integer numberid);

    public List<User>selectAllNumber();
    
    public List<User>selectByDidAll(Integer did);
    
    public List<User>SelectAllDidDepartment(Integer did);
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User selectIdForUser(String id);
    /**
     * 根据名字查询一对一
     * @param username
     * @return
     */
    public User selectBy_Name(String username);
    
    /**
     * 计算当前时间与入职时间的总天数
     * @param number
     * @return
     */
    public Integer selectByRegistertime_NowTime(Integer number);
    /**
     * 获取所有年份
     * @return
     */
    public List<User>selectAll_registertime();
    
    
    public Integer insertSelective(User user);
    
    public Integer updateByPrimaryKey(User user);
    
    
    public Integer deleteByPrimaryKey(String id);
    
    public Integer updateByName(User user);
    
    public Integer updateRegitserTime(User user);
    
    public Integer updatedepartment(User user);
    
    
    public Integer updateBynumbers(User user);
}
