package com.wang.service;

import com.wang.pojo.LeaveRecords;

 
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveRecordsService {
     
	public List<LeaveRecords> selectByExample();
	
	
	public List<LeaveRecords>selectByPrimaryKey(String uid);
	
	public Integer deleteByPrimaryKey(String lid);
	
	public Integer insertSelective(LeaveRecords leaveRecords);
	
	public Integer countByExample();
	
	public Integer updateByPrimaryKeySelective(LeaveRecords leaveRecords);
	
}