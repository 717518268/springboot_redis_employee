package com.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.dao.LeaveRecordsMapper;
import com.wang.pojo.LeaveRecords;
import com.wang.service.LeaveRecordsService;
@Service
@Transactional
public class LeaveRecordsServiceImpl implements LeaveRecordsService{
	
	@Autowired
	private LeaveRecordsMapper leaveRecordsMapper;
	
	@Override
	public List<LeaveRecords> selectByExample() {
		// TODO Auto-generated method stub
		return leaveRecordsMapper.selectByExample();
	}

	@Override
	public List<LeaveRecords> selectByPrimaryKey(String uid) {
		// TODO Auto-generated method stub
		return leaveRecordsMapper.selectByPrimaryKey(uid);
	}

	@Override
	public Integer deleteByPrimaryKey(String lid) {
		// TODO Auto-generated method stub
		return leaveRecordsMapper.deleteByPrimaryKey(lid);
	}

	@Override
	public Integer insertSelective(LeaveRecords leaveRecords) {
		// TODO Auto-generated method stub
		return leaveRecordsMapper.insertSelective(leaveRecords);
	}

	@Override
	public Integer countByExample() {
		// TODO Auto-generated method stub
		return leaveRecordsMapper.countByExample();
	}

	@Override
	public Integer updateByPrimaryKeySelective(LeaveRecords leaveRecords) {
		// TODO Auto-generated method stub
		return leaveRecordsMapper.updateByPrimaryKeySelective(leaveRecords);
	}

}
