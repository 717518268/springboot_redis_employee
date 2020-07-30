package com.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.dao.DepartmentMapper;
import com.wang.pojo.Department;
import com.wang.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Department> selectByAllDepare() {
		 
		return departmentMapper.selectByAllDepare();
	}

	@Override
	public Department selectDepartmentNid(Integer did) {
		// TODO Auto-generated method stub
		return departmentMapper.selectDepartmentNid(did);
	}

	@Override
	public Integer insertDePartments(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.insertDePartments(department);
	}

	@Override
	public Integer updateDepartmentDid(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.insertDePartments(department);
	}

	@Override
	public Department selectDepartmentByDname(String dname) {
		// TODO Auto-generated method stub
		return departmentMapper.selectDepartmentByDname(dname);
	}

	@Override
	public Integer deleteDepartMentById(Integer id) {
		// TODO Auto-generated method stub
		return departmentMapper.deleteDepartMentById(id);
	}

}
