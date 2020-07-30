package com.wang.controller.user;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.pojo.User;
import com.wang.service.UserService;
import com.wang.service.config.UserConfig;

@Controller
public class UserUpdateController {
	 @Autowired
	    private UserService userService;
	 private  Integer threadID;
	 @Autowired
	    private UserConfig userConfig;
	 private String ID;//内部传值
	 private static final Integer ERROR=0;
	 private static final Integer SUCCESS=1;
	 private static final String UPDATEVIEW="updateusercontext.html";//页面
	 
	 @RequestMapping(UPDATEVIEW)
	 public String updateView(@Param("numberid")Integer numberid){
		 if(numberid!=null){
			 threadID=numberid;//将id值set进去
		 }
		 return UPDATEVIEW;
	 }
	 
	 
	 @RequestMapping("/GetUpdate_User")
	 @ResponseBody
	 public User GetUpdateUser(){
		 if(threadID!=null){
			 int numberid=threadID;
			 User user=userService.selectBy_numberid_one(numberid);
			 if(user!=null){
				 ID=user.getId();
				 userConfig.getUser(user);
				 System.out.println(user);
				 return user;
			 }else{
				 return null;
			 }
		 }else{
			 return null;
		 }
	 }
	 
	 
	 /**
	  * 修改信息
	  * @param number
	  * @param username
	  * @param sex
	  * @param registertime
	  * @param did
	  * @return
	  * @throws ParseException
	  */
	 @RequestMapping("/update_UserContexts")
	    @ResponseBody
	    public Integer updateUserContent(@Param("number")Integer number,
	    		@Param("username")String username,@Param("sex")String sex,
	    		@Param("registertime")String registertime,@Param("did")Integer did) throws ParseException{
	    	if(threadID!=null){
	    		
	    		List<User>list=userService.selectAllNumber();
	    		for (User user : list) {
					if(number.equals(user.getNumber())){
						return -1;//修改的编号与其他员工编号重复
					}
				}
	    		if(username!=null||username!=""){
		    		User user=new User();
		    		//user.setId
		    		String id=ID;//将id注入
		    		
		    		user.setId(id);
		    		user.setNumber(number);
		    		user.setUsername(username);
		    		user.setSex(sex);
		    		String newtime=registertime.replace("/","-");//从前端转换格式
		    		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(newtime); 
		    		user.setRegistertime(date);
		    		user.setDid(did);
		    		
		    		int index=userService.updateByPrimaryKey(user);
		    		if(index>0){
		    			
		    			return SUCCESS;//成功回调
		    			
		    		}else{
		    			return ERROR;//返回失败
		    		}
		    		
		    	}
	    	}
	    	   	
	    	
	    	
	    	return ERROR;
	    }
	
	 @RequestMapping("/getr")
	 @ResponseBody
	public String get(){
		String oldtime="2020/12/30";
		System.out.println(oldtime);
		String newtime=oldtime.replace("/","-");
		System.out.println(newtime);
		return newtime;
	}
	 
}
