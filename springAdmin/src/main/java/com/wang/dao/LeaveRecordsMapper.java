package com.wang.dao;

import com.wang.pojo.LeaveRecords;

 
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveRecordsMapper {
     
	public List<LeaveRecords> selectByExample();
	
	
	public List<LeaveRecords>selectByPrimaryKey(String uid);
	
	public Integer deleteByPrimaryKey(String lid);
	
	public Integer insertSelective(LeaveRecords leaveRecords);
	
	public Integer countByExample();
	
	public Integer updateByPrimaryKeySelective(LeaveRecords leaveRecords);
	
}