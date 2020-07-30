package com.wang.controller.index;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.pojo.LoginUser;
import com.wang.service.LoginUserService;
import com.wang.util.GetMD5;

@Controller
public class LoginController {
	
	@Autowired
	private  LoginUserService loginUserService;
	
	private static final Integer SUCCESS=1;
	private static final Integer VALUENULL=0;
	private static final Integer ERROR=-1;
	private static final String[]view={"login1","login2","login3"};
	/*@RequestMapping("/login.html")
	public String AllLoginview(){//登录请求
		System.out.println("进入登录请求，。。。。");
		return "login1";
	}*/
	
	@RequestMapping("/login.html")
	public String AllLoginview(){//登录请求
		//System.out.println("进入登录请求，。。。。");
		int index = (int) (Math.random() * view.length);
		return view[index];
	}
	
	@RequestMapping("/loginMainPethod")
	@ResponseBody
	public Integer loginMainPethod(@Param("username")String username,@Param("password")String password,HttpSession request){
		if(username!=""&&password!=""){
			System.out.println("username"+username+"=====>>>>"+"password"+password);
			
			LoginUser loginUser=new LoginUser();
			loginUser.setUserName(username);
			password=GetMD5.encodeToHex(password);
			loginUser.setPassWord(password);
			 
			 
			LoginUser loginUser2=loginUserService.selectByLogin(loginUser);
			System.out.println(loginUser2);
			if(loginUser2!=null){
				//
				if(loginUser2.getCount()==null){
					loginUser2.setCount(0);
				}
				loginUser2.setCount(loginUser2.getCount()+1);
				int index=loginUserService.updateByCount(loginUser2);
				if(index>0){
					
					
					//存进session
					request.setAttribute("Users",loginUser2);
					return SUCCESS;
				}
				 
			}else{
				return ERROR;
			}
			
			 
		}else{
			return VALUENULL;
		}
		return VALUENULL;
		
		 
	}
	
	@RequestMapping("/loginMainPethodinsertt")
	@ResponseBody
	public Integer inserRegister(){
		LoginUser loginUser=new LoginUser();
		loginUser.setCount(0);
		loginUser.setLid(getUUid());
		loginUser.setCreattime(new Date());
		loginUser.setRuler("root");
		loginUser.setUserName("717518268");
		String password=GetMD5.encodeToHex("wang123456789");
		loginUser.setPassWord(password);
		int index=loginUserService.insert(loginUser);
		
		
		return index;
	}
	
	public String getUUid(){
		  
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
   
}

	
}
