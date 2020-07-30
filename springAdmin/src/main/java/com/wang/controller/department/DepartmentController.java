package com.wang.controller.department;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.pojo.Department;
import com.wang.service.DepartmentService;
import com.wang.service.RedisTempService;
import com.wang.util.GetCode;
import com.wang.util.MyRedisKey;

@CrossOrigin
@Controller

public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RedisTempService redisTempService;
	
	private static final String DEPARTMENTVIEW="inserdepartment.html";
	/**
	 * 添加部门
	 * @return
	 */
	@RequestMapping(DEPARTMENTVIEW)
	public String DepartMentview(){
		return DEPARTMENTVIEW;
	}
	
	
	
	/**
	 * 添加部门
	 * -1重复部门
	 * -2空值异常
	 * 1成功
	 * 0修改语句或者inser语句出错
	 * @param dname
	 * @return
	 */
	@RequestMapping(value="/insert_Controller/Department",method = RequestMethod.POST)
	@ResponseBody
	public Integer insertControllerDepartment(@Param("dname")String dname){
		System.out.println("dname=====>>>>"+dname);
		if(dname!=null||dname!=""){
			Department department=departmentService.selectDepartmentByDname(dname);
			if(department==null){
				Department departments=new Department();
				departments.setDname(dname);
				int nid= Integer.parseInt(GetCode.getMsgCode());//
				departments.setNid(nid);//如果在随机数的情况下出现重复，在上边继续做while处理
				int index=departmentService.insertDePartments(departments);
				if(index>0){
					/**删除key*/
					redisTempService.deleteKey(MyRedisKey.DepartMentList);
					return index>0?1:0;//返回零代表修改出错,1代表成功
				}
				 
			}else{
				return -1;//重复部门
			}
		}
		return -2;//空值异常
		
	}
	
	
	@RequestMapping("/DeleteDepartMentByID")
	@ResponseBody
	public Integer DeleteDepartMentByID(@Param("id")Integer id){
		if(id!=null){
			int deid=departmentService.deleteDepartMentById(id);
			if(deid>0){
				/***
				 * 删除key*/
				redisTempService.deleteKey(MyRedisKey.DepartMentList);
				return 1;//成功
			}
		}
		return 0;//失败
	}
	
	  /**
     * 获取部门信息一个集合
     * @return
     */
    @RequestMapping("/getDepartmentlist")
    @ResponseBody
    public List<Department> getDepartment(){
    	List<Department> departmentlist=(List<Department>) redisTempService.getString(MyRedisKey.DepartMentList);
    	if(departmentlist==null){
    		departmentlist=departmentService.selectByAllDepare();
    		/***存进redis里**/
    		redisTempService.setString(MyRedisKey.DepartMentList, departmentlist, MyRedisKey.TimeOneHouse);
    		System.out.println("从数据库中取....");
    		return departmentlist;
    	}
    	System.out.println("从缓存中取....");
    	return  departmentlist;
    }
    
	
}
