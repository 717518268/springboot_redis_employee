package com.wang.util;

/***
 * 缓存key
 * @author Administrator
 *
 */
public class MyRedisKey {
	
	/**
	 * 缓存部门表的集合key
	 */
	public static final String DepartMentList="DEPARTMENTSLIST";
	
	/***
	 * 一个小时
	 */
	public static final Integer TimeOneHouse=3600;
	
	/**
	 * 单个用户信息时的key
	 */
	public static final String CenterUsers="USERSASID";
	
	/**
	 * 加载单个用户休假表时是所需要的key
	 */
	public static final String LeaveRecordsKey="USERSASIDLeaveRecords";
	
	/***
	 * 根据员工个人的信息
	 * 的休假剩余天数
	 */
	public static final String LeaveCountDay="LEAVECOUNTDAY";
	
	public static final String SetIntegerList20="SetIntegerList20";
	
	public static final String ResignationTableKey="ResignationTable1";
	
	public static final String USERLIST="USERLIST";
}
