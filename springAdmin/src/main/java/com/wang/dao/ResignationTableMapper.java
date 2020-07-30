package com.wang.dao;

import com.wang.pojo.ResignationTable;

 
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResignationTableMapper {
    
	
	/***
	 *根据部门查询已经离职的
	 * @return
	 */
	public List<ResignationTable> selectByPriDepartmentid(String departmentid);
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<ResignationTable> selectByPrimaryAll();
	
	public ResignationTable selectByPrimaryKeyUid_d(String u_id);
	
	public ResignationTable selectByPrimaryUser_name(String user_name);
	
	public Integer deleteByPrimaryKey(String q_id);
	
	public Integer insert(ResignationTable resignationTable);
	
	public Integer insertSelective(ResignationTable resignationTable);
	
	public Integer updateByPrimaryKey(ResignationTable resignationTable);
	
}