package com.wang.controller.user;

import com.wang.pojo.Department;
import com.wang.pojo.ResignationTable;
import com.wang.pojo.User;
import com.wang.service.DepartmentService;
import com.wang.service.RedisTempService;
import com.wang.service.ResignationTableService;
import com.wang.service.UserService;
import com.wang.service.impl.GetDate;
import com.wang.service.config.UserConfig;
import com.wang.util.GetUUID;
import com.wang.util.MyRedisKey;
import com.wang.util.Mycompartable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 查询员工主要
 * @author Administrator
 *
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private DepartmentService departmentService;
    
    /***
     * 声明redis
     */
    @Autowired
    private RedisTempService redisTempService;
    private static final Integer SUCCESS=1;
    private static final Integer ERROE=0;
    private static final Integer ERROE2=-1;
    private static final Integer ERROE3=-2;
    @Autowired
    private UserConfig userConfig;
    
    @Autowired
    private ResignationTableService resignationTableService;
    /**
     * 根据部门id查询
     * 也可以根据年份
     * 
     * @param did
     * @return
     */
    @RequestMapping("/getListUserForRequest")
    @ResponseBody
    public List<User>getListUserForRequest(@Param("did")String  did,@Param("year")String year){
    	 System.out.println(year+"&&&&"+did);
    	 if(did==null&&year==null){
    		 return null;
    	 }
    	 
    	 //查询全部
    	// System.out.println("查全部的值是："+did);
    	 if("all".equals(did)){
    		
    		 
    		 List<User>list= (List<User>) redisTempService.getString(MyRedisKey.USERLIST);
    		 if(list==null){
    			 list= userService.selectAllNumber();
    			 redisTempService.setString(MyRedisKey.USERLIST, list, MyRedisKey.TimeOneHouse);
    		 }
    		  
    		 for (User user : list) {
          		 
     			userConfig.getUser(user);//计算业务
         	}
    		// System.out.println(list);
    		 //这里可以做排序
    		 
    		 
    		 return list;
    	 }
    	 //查询全部end
    	 
    	List<User>list=userService.SelectAllDidDepartment(Integer.parseInt(did));
    	if(list==null){
    		return null;
    	}
    	ArrayList<User>userlist=new ArrayList<>();
    	if("all".equals(year)){//如果是查全部的话默认传递一个
    	//	System.out.println("进入");
    		for (User user : list) {
          		 
    			userConfig.getUser(user);//计算业务
        	}
    	//	System.out.println("user"+list);
    		return list;
    	}else{
    		for (User user : list) {
    			userConfig.getUserAnd(user,Integer.parseInt(year));//计算业务
    				userlist.add(user);	
			}
    		return userlist;//这是根据
    	}
     	
    	 
    }
    
    
    
    /**
     * 根据名字查询
     * @param username
     * @return
     */
    @RequestMapping("/getUsernameForContent")
    @ResponseBody
    public User getUsernameForContent(@Param("username")String username){
    	User user=getUsernameFordepartment(username);
		return user; 
    }
    
    
    public User getUsernameFordepartment(String username){
    	System.out.println("user_name===>>>"+username);
    	if(username!=null){
    		if(!ChineseName(username)){//先判断名字合法
    			return null;
    		}
    			
    		User user=userService.selectBy_Name(username);//
    		if(user!=null){
    			Department department=departmentService.selectDepartmentNid(user.getDid());
        		user.setDepartment(department);//将部门注入进去
        		userConfig.getUser(user);
				//System.out.println("user======>"+user);
				return user;
    		}
    		 
    		 
    		
    	}
		return null; 
    }
    
    /***
     * 离职功能
     * 接入离职表的同时删除在职表的数据
     * @return
     * @throws ParseException 
     */
    @RequestMapping("/SetUpdateUserStautsAnd_delete")
    @ResponseBody
    public Integer SetUpdateUserStauts(@Param("id")String id,@Param("username")String username,@Param("outtime")String outtime) throws ParseException{
    	System.out.println("id=="+id+"\t"+"username"+username+"outtime==="+outtime);
    	if(outtime==null||outtime==""){
    		return ERROE;//直接返回错误
    	}
    	
    	 if(id!=null&&username!=null||username!=""){
    		 //设置post请求
    		 
    		 SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");//离职时间
    		 
    		 User user=getUsernameFordepartment(username);
    		 if(user!=null){
    			 String q_uuid=GetUUID.getUUid();//随机
    			 String uid=user.getId();//uid
    			 //获取部门名称
    			 
    			String department= user.getDepartment().getDname();
    			String workingYears=user.getYearanmethod();//获取工龄
    			Date registertime=user.getRegistertime();//获取入职时间
    			
    		 
    			ResignationTable resignationTable=new ResignationTable();
    			resignationTable.setqId(q_uuid);
    			resignationTable.setuId(uid);
    			resignationTable.setUserName(username);
    			resignationTable.setDepartmentid(department);
    			resignationTable.setWorkingYears(workingYears);
    			resignationTable.setEntryTime(registertime);
    			//dates.parse(outtime);//
    			resignationTable.setExitTime(dates.parse(outtime));
    			resignationTable.setEndupdatetime(new Date());
    			
    			int inser=resignationTableService.insert(resignationTable);
    		//	if(inser>0){
    				int indexdele=userService.deleteByPrimaryKey(id);
    				System.out.println("inser==>>"+inser+"===indexdele=="+indexdele);
    				return (indexdele>0&&inser>0)?SUCCESS:ERROE;
    		//	}
    			
    		 }else{
    			 return ERROE;
    		 }
    		 
    
    	 }else{
    		 //空值，非法请求
    		 return ERROE2;
    	 }
    	
		 
    }
    
    
  

	 public   boolean ChineseName(String name) {
		 if(name==null) {
			 return false;
		 }
		 if (!name.matches("[\u4e00-\u9fa5]{2,4}")) {
		 System.out.println("只能输入2到4个汉字");
		 return false;
		 }else {
			 return true;
		 }
		 
		 }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Integer deleteUserById(@Param("id")String id){
    	System.out.println("执行删除请求+++"+id);
    	if(id!=""||id!=null){
    		int deletevalue=userService.deleteByPrimaryKey(id);
    		if(deletevalue>0){
    			return SUCCESS;
    		}
    	} 
		return ERROE;
    }
    
    /**
     * 页面请求
     * @return
     */
    @RequestMapping("/user.html")
    public String userView(){
    	return "user";
    }
    
    
    /**
     * 获取所有年份
     * 这是取员工所有入职时间
     * @return
     */
  /*  @RequestMapping("/get_Sort_years_List")
    @ResponseBody
    public Set<Integer> getlistRegister(){
    	List<User>listregistertime=userService.selectAll_registertime();
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
    	Set<Integer>setregistertime=new HashSet<Integer>();
    	for (User user : listregistertime) {
    		 
			String years=sdf.format(user.getRegistertime());
			String []arryear=years.split("-");//将字符串中的日期分割
			setregistertime.add(Integer.parseInt(arryear[0]));
			//提取数组里下标为零的第一个，年份
		}
    	 
    	TreeSet<Integer>treset=new TreeSet<>(new Mycompartable());//排序
    	treset.addAll(setregistertime);
    	
    	return treset;
    }*/
    
    @RequestMapping("/get_Sort_years_List")
    @ResponseBody
    public Set<Integer> getlistRegister2(){
    	
    	Set<Integer>setregistertime=getSetListInteget();
    	
    		
 
    	return setregistertime;
    }
    
    /**
     * 内部私有集合
     * @return
     */
    private Set<Integer>getSetListInteget(){
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
    	Set<Integer>setregistertime=new HashSet<Integer>();
   
    	LocalDate localtime=LocalDate.now(); 
    	int localnowyear=localtime.getYear();
    	//取20
    	//当前手机
    	for (int i = localnowyear; i >localnowyear-20; i--) {
    		setregistertime.add(i);
		}
    	
    	TreeSet<Integer>treset=new TreeSet<>(new Mycompartable());//排序
    	treset.addAll(setregistertime);
    	return treset;
    }
}
