package com.wang.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.dao.UserMapper;
import com.wang.pojo.User;
import com.wang.service.UserService;
import com.wang.util.GetUUID;

@Controller
public class UserInserController {
	
	private static final String VIEWINSERT="inseruser.html";
	private static final Integer SUCCESS=1;
	private static final Integer ERROR=-1;
	@Autowired
	private UserService  userService;
	
	@RequestMapping(VIEWINSERT)
	public String UserInserView(){
		
		
		return VIEWINSERT;
	}
	
	/**  newnumber:newnumber,username:username,sex:sex,registertime:registertime,department:department  
	 * 添加一个员工
	 * @throws ParseException ***/
	@RequestMapping("/insertUser_for_Context")
	@ResponseBody
	public Integer inserControllerSend(@Param("newnumber")Integer newnumber,
			@Param("username")String username,@Param("sex")String sex,
			@Param("registertime")String registertime,@Param("department")Integer department) throws ParseException{
		
		boolean numflg=isfalgContent(newnumber);//如果是false就正确
		int index=ISimpEntynumber(numflg,username,registertime);
		if(index==1){
			
			User user=new User();
			user.setId(GetUUID.getUUid());//uuid
			user.setNumber(newnumber);
			user.setSex(sex);
			user.setUsername(username);
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(registertime); 
			user.setRegistertime(date);
			user.setDid(department);
			int inser=userService.insertSelective(user);
			System.out.println(user);
			if(inser>0){
				return SUCCESS;
			}
		}
		return ERROR;
		                                                                 
		
	}
 
 
	
 
	public Integer ISimpEntynumber(boolean numflg,String username,String registertime){
		
		return numflg==false?ISimpEntyUserName(username,registertime):-1;
			 //编号出现重复或者是空值 
	}
	
	public Integer ISimpEntyUserName(String username,String registertime){
		return ChineseName(username)==true?ISimpEntySex(registertime):-2;//这种是汉字匹配不正确
	}
	
	 
	
	public Integer ISimpEntySex(Object value2){
		return (value2!=null||value2!="")==true?1:-3;//日期不能为空
			 
		 
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
	 * 判断编号是否重复和空判断
	 * @param value
	 * @return
	 */
	public boolean isfalgContent(Object value){
		 boolean flg=true;
		if(value!=null||value!=""){
			List<User>list=userService.selectAllNumber();
			for (User user : list) {
				if(value.equals(user.getNumber())){
					 return flg;//编号已经存在
				}
			}
		} 
		flg=false;
		return flg;
		
	}
	
	
}
