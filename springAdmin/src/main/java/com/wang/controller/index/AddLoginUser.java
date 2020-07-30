package com.wang.controller.index;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.pojo.LoginUser;
import com.wang.service.LoginUserService;
import com.wang.util.GetMD5;
import com.wang.util.GetUUID;

@Controller
public class AddLoginUser {
	@Autowired
	private  LoginUserService loginUserService;
	
	
	@RequestMapping("/adduserrule.html")
	public String addruleview(){
		
		return "adduserrule";
	}
	
	@RequestMapping("/adduse/InserRule")
	@ResponseBody
	public Integer InserRule(@Param("username")String username,@Param("rule")String rule,@Param("password")String password){
		System.out.println("username===>>"+username+"rule===>"+rule+"password==>>"+password);
		if(username!=""&&password!=""){
			LoginUser loginUser=new LoginUser();
			loginUser.setCount(0);
			loginUser.setLid(GetUUID.getUUid());
			loginUser.setCreattime(new Date());
			if(rule==""){
				rule="user";
			}
			loginUser.setRuler(rule);
			loginUser.setUserName(username);
			 password=GetMD5.encodeToHex(password);
			loginUser.setPassWord(password);

			int dex=loginUserService.insert(loginUser);
			if(dex>0){
				return 1;
			}
		}
		
		return 0;
	}
	
	
	
	 
}
