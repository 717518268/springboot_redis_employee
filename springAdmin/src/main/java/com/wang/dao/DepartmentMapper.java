package com.wang.dao;

import java.util.List;

import com.wang.pojo.Department;

public interface DepartmentMapper {

    public List<Department> selectByAllDepare();
    
    /**
     * 根据部门编号
     * @param did
     * @return
     */
    public Department selectDepartmentNid(Integer did);
    
    
    /**
     * 返回主键
     * @param department
     * @return
     */
    public Integer insertDePartments(Department department);
    
    public Integer updateDepartmentDid(Department department);
    
    /**
     * 查询数据库有没有重复出现的部门名称
     * @param dname
     * @return
     */
    public Department selectDepartmentByDname(String dname);
    
    /**
     * 根据部门id删除
     * @param id
     * @return
     */
    public Integer deleteDepartMentById(Integer id); 
}
