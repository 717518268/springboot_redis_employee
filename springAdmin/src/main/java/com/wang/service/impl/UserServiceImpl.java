package com.wang.service.impl;

import com.wang.dao.UserMapper;
import com.wang.pojo.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;


    @Override
    public User selectBynumberid(Integer numberid) {
        return usermapper.selectBynumberid(numberid);
    }

    @Override
    public List<User> selectAllNumber() {
        return usermapper.selectAllNumber();
    }

	@Override
	public List<User> selectByDidAll(Integer did) {
		// TODO Auto-generated method stub
		return usermapper.selectByDidAll(did);
	}

	@Override
	public List<User> SelectAllDidDepartment(Integer did) {
		// TODO Auto-generated method stub
		return usermapper.SelectAllDidDepartment(did);
	}

	@Override
	public User selectBy_Name(String username) {
		// TODO Auto-generated method stub
		return usermapper.selectBy_Name(username);
	}

	@Override
	public Integer insertSelective(User user) {
		// TODO Auto-generated method stub
		return usermapper.insertSelective(user);
	}

	@Override
	public Integer updateByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		return usermapper.updateByPrimaryKey(user);
	}

	@Override
	public Integer deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return usermapper.deleteByPrimaryKey(id);
	}

	@Override
	public User selectIdForUser(String id) {
		// TODO Auto-generated method stub
		return usermapper.selectIdForUser(id);
	}

	@Override
	public List<User> selectAll_registertime() {
		// TODO Auto-generated method stub
		return usermapper.selectAll_registertime();
	}

	@Override
	public User selectBy_numberid_one(Integer numberid) {
		// TODO Auto-generated method stub
		return usermapper.selectBy_numberid_one(numberid);
	}

	@Override
	public Integer updateByName(User user) {
		// TODO Auto-generated method stub
		return usermapper.updateByName(user);
	}

	@Override
	public Integer updateRegitserTime(User user) {
		// TODO Auto-generated method stub
		return usermapper.updateRegitserTime(user);
	}

	@Override
	public Integer updatedepartment(User user) {
		// TODO Auto-generated method stub
		return usermapper.updatedepartment(user);
	}

	@Override
	public Integer updateBynumbers(User user) {
		// TODO Auto-generated method stub
		return usermapper.updateBynumbers(user);
	}

	@Override
	public Integer selectByRegistertime_NowTime(Integer number) {
		// TODO Auto-generated method stub
		return usermapper.selectByRegistertime_NowTime(number);
	}
}
