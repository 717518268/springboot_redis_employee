package com.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.dao.ResignationTableMapper;
import com.wang.pojo.ResignationTable;
import com.wang.service.ResignationTableService;

@Transactional
@Service
public class ResignationTableServiceImpl implements ResignationTableService{
	
	@Autowired
	private ResignationTableMapper resignationTableMapper;
	
	@Override
	public ResignationTable selectByPrimaryKeyUid_d(String u_id) {
		// TODO Auto-generated method stub
		return resignationTableMapper.selectByPrimaryKeyUid_d(u_id);
	}

	@Override
	public ResignationTable selectByPrimaryUser_name(String user_name) {
		// TODO Auto-generated method stub
		return resignationTableMapper.selectByPrimaryUser_name(user_name);
	}

	@Override
	public Integer deleteByPrimaryKey(String q_id) {
		// TODO Auto-generated method stub
		return resignationTableMapper.deleteByPrimaryKey(q_id);
	}

	@Override
	public Integer insert(ResignationTable resignationTable) {
		// TODO Auto-generated method stub
		return resignationTableMapper.insert(resignationTable);
	}

	@Override
	public Integer insertSelective(ResignationTable resignationTable) {
		// TODO Auto-generated method stub
		return resignationTableMapper.insertSelective(resignationTable);
	}

	@Override
	public Integer updateByPrimaryKey(ResignationTable resignationTable) {
		// TODO Auto-generated method stub
		return resignationTableMapper.updateByPrimaryKey(resignationTable);
	}

	@Override
	public List<ResignationTable> selectByPriDepartmentid(String departmentid) {
		// TODO Auto-generated method stub
		return resignationTableMapper.selectByPriDepartmentid(departmentid);
	}

	@Override
	public List<ResignationTable> selectByPrimaryAll() {
		// TODO Auto-generated method stub
		return resignationTableMapper.selectByPrimaryAll();
	}

}
