package com.wang.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.pojo.Department;
import com.wang.pojo.LeaveRecords;
import com.wang.pojo.User;
import com.wang.service.DepartmentService;
import com.wang.service.LeaveRecordsService;
import com.wang.service.RedisTempService;
import com.wang.service.UserService;
import com.wang.service.config.UserConfig;
import com.wang.service.impl.GetDate;
import com.wang.util.GetUUID;
import com.wang.util.MyRedisKey;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工个人中心
 * @author Administrator
 *
 */
@Slf4j
@Controller
public class CenterUserController {
	
	private final Logger logger = LoggerFactory.getLogger(CenterUserController.class);
	
	private   String threadlocalid;
	//变量接收传进来的id值
	@Autowired
	private UserService userService;
	/**部门表**/
	@Autowired
	private DepartmentService departmentService;
	/**休假表**/
	@Autowired
	private LeaveRecordsService leaveRecordsService;
	
	private User  usetlocal;
	private static final Integer SUCCESS=1;
	private static final Integer ERROR1=-1;
	private static final Integer ERROR2=-2;
	private static final Integer ERRORNULL=-3;
	private static final Integer MAXERROR=-4;
	
	private static final Integer METNONERROR=-5;
	 
	private static final Integer YEARERROR=-6;
	private static final String Contacts="contacts.html";
	@Autowired
	private RedisTempService redisTempService;
	@Autowired
	private UserConfig userConfig;
	
	@RequestMapping("/contacts.html")
	public String prosionUSerView(@Param("id")String id){
		if(id!=null ){
			this.threadlocalid=id;
		}
		
		return "contacts";
	}
	
	
	/***
	 * 首先不能等于null
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/selectContentUser_aContacts")
	@ResponseBody
	public User selectContentUser(){
		if(this.threadlocalid!=null){
			
			 User  user=userService.selectIdForUser(this.threadlocalid);
		 
				 
				 if(user!=null){//防止用户直接在url输入id号
						Department department= departmentService.selectDepartmentNid(user.getDid());
						if(department!=null){
							user.setDepartment(department);//将部门也存进映射表里
						}
						usetlocal=user;//放进本地线程
						userConfig.getUser(user);
						
						 
						return user;
					}else{
						return null;
					}
			  		 
		} 
		return null;
		
	}
	
	
	/***
	 * 修改员工编号
	 * @param number
	 * @return
	 */
	@RequestMapping("/UpdateNumberReq")
	@ResponseBody
	public Integer UpdateNumberReq(@Param("updatenumber")Integer updatenumber){
		if(updatenumber==null){
			return ERRORNULL;//不能为空-3
		}
			
		User user=userService.selectBy_numberid_one(updatenumber);
		if(user==null){//先判断是否为空对象，不能重复的情况下
			User usersupdatenumber=new User();
			usersupdatenumber.setId(this.threadlocalid);
			usersupdatenumber.setNumber(updatenumber);
			int indexupdatenumber=userService.updateBynumbers(usersupdatenumber);
			 
			return indexupdatenumber>0?SUCCESS:ERROR1;//如果成功就返回1否则就返回
		}else{
			//已经有此员工号了，重复number
			return ERRORNULL;
		}
			
		
	}
	
	
	
	
	/**
	 * 修改名字
	 * @return
	 */
	@RequestMapping("/updateName")
	@ResponseBody
	public Integer updateNameReqs(@Param("updatename")String updatename){
		if(updatename==null||updatename==""){
			return ERROR1;//-1
		}
	//	System.out.println("11111"+updatename);
		if(this.threadlocalid!=null){
			User user=userService.selectIdForUser(this.threadlocalid);//先查
			//System.out.println("2222222");
			if(user!=null){
				User users=new User();
				users.setUsername(updatename);
				users.setNumber(user.getNumber());
				 
				//System.out.println("3333333");
				int index=userService.updateByName(users);
				//System.out.println("index==="+index);
				 
				if(index>0){
					return SUCCESS;//成功回调
				}
			}else{
				return ERROR2;//-2
			}
			 
			
			
		}
		
		
		return null;
	}
	
	/**
	 * 修改入职时间
	 * @return
	 */
	@RequestMapping("/updateRegitserTime")
	@ResponseBody
	public Integer updateRegitserTime(@Param("newtimes")String newtimes){
		if(newtimes==null||newtimes==""){
			return ERROR1;//-1
		}
		if(this.threadlocalid!=null){
			User user=userService.selectIdForUser(threadlocalid);//先查
			if(user!=null){
				 
				User users=new User();
				
				try {
					SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date date=dates.parse(newtimes);
					users.setRegistertime(date);
					users.setNumber(user.getNumber());
					 
				} catch (ParseException e) {
					 
					e.printStackTrace();
				}
				
				int index=userService.updateRegitserTime(users);
				if(index>0){
					return SUCCESS;//成功回调
				}
			}else{
				return ERROR2;//-2
			}
			 
			
			
		}
		
		
		return null;
	}
	
	
	/**
	 * 修改部门
	 -1不能为空
	 1成功回调
	 2空指针异常&&部门不存在
	 * @return
	 */
	@RequestMapping("/updatedepartment")
	@ResponseBody
	public Integer updatedepartment(@Param("updname")String updname//部门名字
			){
		if(updname==null||updname==""){
			return ERROR1;//-1不能为空
		}
		if(this.threadlocalid!=null){
			User user=userService.selectIdForUser(threadlocalid);//先查
			if(user!=null){
				Department department=departmentService.selectDepartmentByDname(updname);
				//先获取部门信息
				if(department!=null){//倘若部门存在
					User users=new User();
					int did=department.getNid();
					users.setNumber(user.getNumber());
					users.setDid(did);//只需要修改部门id
					 
					int index=userService.updatedepartment(users);
					if(index>0){
						return SUCCESS;//成功回调
					}
				}else{
					return ERROR2;//部门不存在
				}
				 
			}else{
				return ERROR2;//-2空指针异常
			}
			 
			
			
		}
		
		
		return null;
	}
	
	
	/****
	 * 页面加载时就进行将休假列表加载出去
	 * 根据内部传递id值
	 * this.threadlocalid
	 * @return
	 */
	@RequestMapping("/getLeave/Recordslist")
	@ResponseBody
	public List<LeaveRecords>getLeaveRecords(){
		List<LeaveRecords> listleav=(List<LeaveRecords>) redisTempService.getString(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid);
		if(listleav==null){
			listleav=leaveRecordsService.selectByPrimaryKey(this.threadlocalid);
			redisTempService.setString(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid, listleav, MyRedisKey.TimeOneHouse);//缓存一个小时
			
			return listleav;
		}
		   
		return listleav;
	}
	
	/**
	 * 根据年份过滤休假信息
	 * 
	 * @param year1
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping("/getLeave/Recordslist_year")
	@ResponseBody
	public List<LeaveRecords>getLeaveRecordsBuyYears(@Param("year")String year){
		logger.debug("now  this value====>>>"+year+"result  ");
		System.out.println("year1============"+year);
		List<LeaveRecords> listleav;
		if(this.threadlocalid!=null){
			if(year!=null||year!=""){//根据年份筛选出
				 listleav=leaveRecordsService.selectByPrimaryKey(this.threadlocalid);
				 ArrayList<LeaveRecords>array=new ArrayList<>();
				 for (LeaveRecords lrs : listleav) {
					 if(year.equals(getStringdata(lrs.getLeaveYearend()))){
						 array.add(lrs);
					 }
					 
				}
				 return array;
			}else{
				 listleav=leaveRecordsService.selectByPrimaryKey(this.threadlocalid);
				 return listleav;
			}
		 
		}
		 
		return null;
		
	}
	/**
	 {starttime:starttime,endtime:endtime,stauts:stauts,reason:reason}
	 1=成功
	 -1=空值
	 -2=未知异常
	 -3=空值
	 -4时间超出
	 根据当前员工编号
	 添加一个休假信息，
	 等价于休假申请
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/sendLeava_holiday")
	@ResponseBody
	public Integer sendLeava_holiday(@Param("starttime")String starttime,//开始时间
			@Param("endtime")String endtime,//结束时间
			@Param("stauts")String stauts,//状态
			@Param("reason")String reason//理由
			) throws ParseException{
	
		 
		if(starttime==null&&endtime==null&&stauts==null||stauts==""&&reason==null||reason==""){
			return ERRORNULL;//-3空值
		}
			//先进行空判断
			if(usetlocal!=null){//先去线程本地看
				String []arr=getinputDate(starttime);
				String []arr2=getinputDate(endtime);//	System.out.println(arr[0]+""+arr[1]+""+arr[2]);
				if(arr[0].equals(arr2[0])){//年份是否相等
					if(arr[1].equals(arr[1])){//月份是否相等
						if(Integer.parseInt(arr[2])<Integer.parseInt(arr2[2])){//时间是多少System.out.println(Integer.parseInt(arr2[2])-Integer.parseInt(arr[2]));	
							List<LeaveRecords> listleav=leaveRecordsService.selectByPrimaryKey(this.threadlocalid);
							if(listleav!=null){//假设休假表不等于空
							 int count=0;
								for (LeaveRecords leRes : listleav) {
									count+=leRes.getLcount();//累计总休假天数
								}
								User users=this.usetlocal;//取可休假的总天数
								if(users!=null){
									int total=users.getCount();
									if(count<total){//如果休假表不为空取全部的值进来与当前可休假做比较
										//可休假 顺便插入数据库
										int StartEndtime=Integer.parseInt(arr2[2])-Integer.parseInt(arr[2]);
										int resultcount=StartEndtime;
										if((StartEndtime+count)<users.getCount()){//天于天的时间再减，得出的和小于可以休假的总天数
											String datestart=Integer.parseInt(arr[0])+"-"+Integer.parseInt(arr[1])+"-"+Integer.parseInt(arr[2]);
											String dateend=Integer.parseInt(arr2[0])+"-"+Integer.parseInt(arr2[1])+"-"+Integer.parseInt(arr2[2]);
											LeaveRecords  leaveRecords=getLeaveRecords(users.getId(), resultcount, reason, datestart, dateend, stauts);
											int indexreuslyt=leaveRecordsService.insertSelective(leaveRecords);
											if(indexreuslyt>0){
												//删除redis休假表key
												if( redisTempService.getString(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid)!=null){
													redisTempService.deleteKey(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid);
												}
												 //end delete redis
												
												return SUCCESS;//成功
											}
											}else{
												return MAXERROR;//休假天数大于可休假天数
											}
								 
										 		}else if(count>total){//休假天数大于可休假天数
										 			return MAXERROR;
												}
										
								} else{//休假表等于空
										users=this.usetlocal;//取可休假的总天数
										if(users!=null){
											
										int StartEndtime=Integer.parseInt(arr2[2])-Integer.parseInt(arr[2]);
										int resultcount=StartEndtime;//
										if(StartEndtime<users.getCount()){//天于天的时间再减，得出的和小于可以休假的总天数
											String datestart=Integer.parseInt(arr[0])+"-"+Integer.parseInt(arr[1])+"-"+Integer.parseInt(arr[2]);
											String dateend=Integer.parseInt(arr2[0])+"-"+Integer.parseInt(arr2[1])+"-"+Integer.parseInt(arr2[2]);
											//将年份月份日拼接成一个字符串
											LeaveRecords  leaveRecords=getLeaveRecords(users.getId(), resultcount, reason, datestart, dateend, stauts);
											int indexreuslyt=leaveRecordsService.insertSelective(leaveRecords);
											//插入
											if(indexreuslyt>0){
												//删除redis休假表key
												if( redisTempService.getString(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid)!=null){
													redisTempService.deleteKey(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid);
												}
												 //end delete redis
												
												return SUCCESS;//成功
											}
											}else{
												return MAXERROR;//休假大于可休假的
											}
							

									}else{
										return METNONERROR;//月份超出
									}
								
									}
							 
						}else{
							return YEARERROR;//年份不相等超出年份
						}
					}//
				}
				
				 
				return ERROR2;
			}
			
			
		}
		return ERROR2;
		
		 
		
		
	//	return null;
		
	}
	
	
	@RequestMapping("/getYESCount")
	@ResponseBody
	public Integer getYESCount(){
		User users=this.usetlocal;
		
		//List<LeaveRecords> listleav=(List<LeaveRecords>) redisTempService.getString(MyRedisKey.LeaveCountDay+""+this.threadlocalid);
		//if(listleav)
		List<LeaveRecords> listleav=leaveRecordsService.selectByPrimaryKey(this.threadlocalid);
		if(listleav!=null){
			 
			int cout=0;
			if(users!=null){
				for (LeaveRecords leaveRecords : listleav) {
					cout+=leaveRecords.getLcount();//累计总休假天数
				}
				
				return (users.getCount()-cout);//返回休假表与当前的可休假天数
				
			}
		}else{
			if(this.threadlocalid!=null){
				User nowUser=userService.selectIdForUser(this.threadlocalid);
				userConfig.getUser(nowUser);
				return nowUser.getCount();//如果休假表为空，就直接返回可休假天数
			}
		}
		return null;
	}
	
	
	/**
	 * 根据id删除
	 * @param lid
	 * @return
	 */
	@RequestMapping("/DeleteLeaveRecords_lids")
	@ResponseBody
	public Integer DeleteLeaveRecords(@Param("lid")String lid){
		if(lid!=null){
			int index=leaveRecordsService.deleteByPrimaryKey(lid);
			//int index=1;
			if(index>0){
				//删除redis休假表key
				if( redisTempService.getString(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid)!=null){
					redisTempService.deleteKey(MyRedisKey.LeaveRecordsKey+""+this.threadlocalid);
				}
				 //end delete redis
				return SUCCESS;
			}else{
				return ERROR1;
			}
		}
		return null;
		
	}
	
	
	
	
	
	
	private LeaveRecords getLeaveRecords(String id,Integer resultcount,String reason,String datestart,String dateend,String stauts) throws ParseException{
		LeaveRecords  leaveRecords=new LeaveRecords();
		leaveRecords.setLid(GetUUID.getUUid());
	//	leaveRecords.setUid(users.getId());
		leaveRecords.setUid(id);
		leaveRecords.setLcount(resultcount);
		leaveRecords.setLeave_reason(reason);//原因
		 
		leaveRecords.setLeaveYearstart(getdataes(datestart));//
		leaveRecords.setLeaveYearend(getdataes(dateend));
		leaveRecords.setInputTime(GetDate.getdateDate());//传入date
		leaveRecords.setStauts(stauts);//状态
		
		return leaveRecords;
		
	}
	
	
	
	
	/*
	  
	 */
	private String[] getinputDate(String value) throws ParseException{
		Date sdf =new SimpleDateFormat("yyyy-MM-dd").parse(value);
		
		SimpleDateFormat stdfg=new SimpleDateFormat("yyyy-MM-dd");
		String timevalue=stdfg.format(sdf);
		 
		return getArraylisDate(timevalue);
	}
	
	private String[]getArraylisDate(String getnowdate){
		
		return getnowdate.split("-");
	}
	
	
	 
	
	 
	public Date getdataes(String date) throws ParseException{
		
		 
		Date da=new  SimpleDateFormat("yyyy-MM-dd").parse(date);
		 
		return da;
		
	}
	
	public String getStringdata(Date value){
		SimpleDateFormat da=new  SimpleDateFormat("yyyy-MM-dd");
		String splarrValue=da.format(value);
		String[] arr=splarrValue.split("-");
		return arr[0];//将年份转成String
	}

}
