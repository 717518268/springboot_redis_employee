package com.wang.controller.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.pojo.ResignationTable;
import com.wang.service.RedisTempService;
import com.wang.service.ResignationTableService;
import com.wang.service.UserService;
import com.wang.util.MyRedisKey;

@Controller
public class ClientsQuitController {
	
	private static final String ClientsView="clients.html";
	@Autowired
	private UserService userService;
	@Autowired
	private ResignationTableService resignationTableService;
	@Autowired
	private RedisTempService redisTempService;
	@RequestMapping("/clients.html")
	public String ClientsView(){
		
		return "clients";
		
	}
	
	
	
	@RequestMapping("/getResignation/ListAll")
	@ResponseBody
	public List<ResignationTable>getResignationList(){
		
		List<ResignationTable> listResisginh=(List<ResignationTable>) redisTempService.getString(MyRedisKey.ResignationTableKey);
		System.out.println(listResisginh);
		if(listResisginh==null){
			listResisginh=resignationTableService.selectByPrimaryAll();
			//存进缓存里
			redisTempService.setString(MyRedisKey.ResignationTableKey, listResisginh, MyRedisKey.TimeOneHouse);
			System.out.println("go mysql ....");
			return listResisginh;
		}
		System.out.println("go redis ....");
		
		
		return listResisginh;
		
	}
	
	
	
	
	 
	
	
	
	
	
	
	@RequestMapping("/test11")
	@ResponseBody
	public Double getDay(){
		double yea=365;
		
		return (double) (userService.selectByRegistertime_NowTime(147)/yea);
	}
	
	@RequestMapping("/test12")
	@ResponseBody
	public String getDa22y(){
		double yea=365;
		
		return  String.format("%2f",1099/yea);
	}
}
