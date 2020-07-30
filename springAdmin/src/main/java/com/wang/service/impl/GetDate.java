package com.wang.service.impl;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  
 * @author Administrator
 *
 */
public class GetDate {
	
	/**
	 * 获取时间
	 * @return
	 */
	public static synchronized String getdate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sd=sdf.format(new Date());
		return sd;
	}
	
	public static synchronized Date getdateDate(){
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		return new Date();
	}
	/**
	 * 获取时间
	 * 年月日
	 * @return
	 */
	public static synchronized String getdatenomines(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sd=sdf.format(new Date());
		return sd;
	}
	/**
	 * 获取时间
	 * @return
	 */
	public static synchronized String getdate2noyear(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd HH:mm:ss");
		String sd=sdf.format(new Date());
		return sd;
	}
	
	/**
	 * 匹配数字
	 * @return
	 */
	public static  String getNumberData(){
		
		String nowdatda=getdate();


		String regEx="[^0-9]";
		
		Pattern per=Pattern.compile(regEx);
		Matcher match=per.matcher(nowdatda);
		String index=match.replaceAll("").trim();
		return index;
	}
	/**
	 * 匹配数字
	 * @return
	 */
	public static  String getNumberData2noye(){
		
		String nowdatda=getdate2noyear();


		String regEx="[^0-9]";
		
		Pattern per=Pattern.compile(regEx);
		Matcher match=per.matcher(nowdatda);
		String index=match.replaceAll("").trim();
		return index;
	}
	
	public static void main(String[] args) {
		System.out.println(getdate2noyear());
	}

	 
	
}
