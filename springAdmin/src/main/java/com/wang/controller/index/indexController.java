package com.wang.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	
	@RequestMapping("/")
	public String indexView(){
		
		return "index";
	}
	
	
	@RequestMapping("/500.html")
	public String graph_echartsView(){
		
		return "500";
	}
	
	
	 
	
	
}
