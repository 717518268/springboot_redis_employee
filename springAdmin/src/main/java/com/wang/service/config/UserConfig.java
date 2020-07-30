package com.wang.service.config;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wang.pojo.User;
import com.wang.service.UserService;
import com.wang.service.impl.GetDate;

/***
 * 计算工龄
 * @author Administrator
 *
 */
@Component
public class UserConfig {
	
	/***最大休假日**/
	private static final Integer DEFUALT=15;
    
	/**将默认值设置为4，如果计算出满一年的就从这个基础上+1*/
    private static final Integer DEFAULTCOUNT=4;
    
    /**未满一年的工龄全部设置为0天休假*/
    private static final   Integer DEFALUTNULL=0;
    
    private static final Integer MAXMETH=12;
    /**一年的单位*/
    private static final Integer YEARCOUNT=365;
    @Autowired
	private UserService userService;
   // private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
	 /**
     * 传入对象计算工龄和休假天数
     * @param user
     * @return
     */
  /*  public  User getUser(User user){
    	if(user!=null){
    	//	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        	String data=sdf.format(user.getRegistertime());
        	String[] arr=data.split("-");
        	int years=Integer.parseInt(arr[0]);
        	int menth=Integer.parseInt(arr[1]);
        	int day=Integer.parseInt(arr[2]);
        	//获取当前时间
        	String nowdata=GetDate.getdatenomines();
        	String[] nowarr=nowdata.split("-");
        	int nowyear=Integer.parseInt(nowarr[0]);
        	int nowmenth=Integer.parseInt(nowarr[1]);
        	int nowday=Integer.parseInt(nowarr[2]);
        	int isment=nowmenth-menth;
        	nowyear=nowyear-years;//当前时间减去入职时间	 
        	String YearOld=nowyear+"年"+isment+"月";
        	user.setYearanmethod(YearOld);
        	user.setCount(DEFAULTCOUNT);//天数
        	int i=1;
        	while(nowyear>=i){
        			user.setCount(user.getCount()+1);//将休假的天数累加
            		i++;	 
        	}
        	int reslut=nowmenth-menth;
        	if(reslut<0){//当前月份减去入职月份
        		user.setCount(user.getCount()+1);
        	}else if(reslut==0){//当月份相等时
        		int resultday=nowday-day;//当前时间减掉入职时间的天数（号）
        		if(resultday<=0){
        	
    //相同的月份的情况下，根据天数来计算是否有365天 ，多了一天，多一天之后就算第二年
        			user.setCount(user.getCount()+1);
        		}
        	}
        	if(user.getCount()==4){//如果是第一年 入职也就是把休假设置为零
        		user.setCount(DEFALUTNULL);
        	}
        	if(user.getCount()>DEFUALT){//年假最多不能大于15
        		user.setCount(DEFUALT);//如果大于15就设置最大休假天数为15
        	}
    	}
    	
    	return null;
    }
    */
    
    /**
     * 根据时间
     * @param user
     * @param nowyear
     * @return
     */
    public static User getUserAnd(User user,Integer nowyear){
    	if(user!=null){
    		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        	String data=sdf.format(user.getRegistertime());
        	
        	String[] arr=data.split("-");
        	int years=Integer.parseInt(arr[0]);
        	int menth=Integer.parseInt(arr[1]);
        	int day=Integer.parseInt(arr[2]);
        	//获取当前时间
        	String nowdata=GetDate.getdatenomines();
        	String[] nowarr=nowdata.split("-");
        	/** 根据外面传进来的时间去计算工龄  **/
        	int nowmenth=Integer.parseInt(nowarr[1]);
        	int nowday=Integer.parseInt(nowarr[2]);
        	nowyear=nowyear-years;//当前时间减去入职时间
        	int isment=nowmenth-menth;//当前月份
        	nowmenth=nowmenth-menth;
        	//nowmenth+=2;
        	if(nowyear<0){//如果这个年份计算出来的结果是负数,这步对查询过去年份的很关键，
        		nowyear=0;//就直接等于零
        		nowmenth=0;//月份也设置为零
        	}
        	 
        	String YearOld=nowyear+"年"+isment+"月";
        	user.setYearanmethod(YearOld);
        	user.setCount(DEFAULTCOUNT);//天数
        	int i=1;
        	 
        	while(nowyear>=i){
        		 
        			user.setCount(user.getCount()+1);//将休假的天数累加
            		i++;	 
        	}
        	
        	int reslut=nowmenth-menth;
        	if(reslut>0){//当前月份减去入职月份
        		user.setCount(user.getCount()+1);
        	}else if(reslut==0){//当月份相等时
        		int resultday=nowday-day;//当前时间减掉入职时间的天数（号）
        		if(resultday<=0){
        	//相同的月份的情况下，根据天数来计算是否有365天 ，多了一天，多一天之后就算第二年
        			user.setCount(user.getCount()+1);
        		}
        	}
        	
        	if(user.getCount()==4){//如果是第一年 入职也就是把休假设置为零
        		user.setCount(DEFALUTNULL);
        	}
        	
        	if(user.getCount()>DEFUALT){//年假最多不能大于15
        		user.setCount(DEFUALT);//如果大于15就设置最大休假天数为15
        	}
    	}
    	
    	return null;
    }
    
    
    /**
     * sql计算版
     * @param user
     * @return
     */
    public  User getUser(User user){
    //	System.out.println(user);
    	if(user!=null){
    		 
        	 //取入职总天数
    		 int reuslt=getResultCount(user.getNumber());
    		 
    		 //先取年份(总天数/365=年)
    		 int resultyear=reuslt/YEARCOUNT;
    		 //System.out.println("reuslt===>>>"+reuslt+"&&resultyear===>>>"+resultyear);
    		 int i=0;
    		 user.setCount(DEFAULTCOUNT);//天数默认设置4
    		 if(resultyear>0){
    			 while(i<resultyear){
        			 user.setCount(user.getCount()+1);
        			 i++;
        		 }
    		 }
    		  
    		 
    		String YearOld=resultyear+"年|共："+reuslt+"天";
        	user.setYearanmethod(YearOld);
        	 
        	
    	 
        	if(user.getCount()==4){//如果是第一年 入职也就是把休假设置为零
        		user.setCount(DEFALUTNULL);
        	}
        	
        	if(user.getCount()>DEFUALT){//年假最多不能大于15
        		user.setCount(DEFUALT);//如果大于15就设置最大休假天数为15
        	}
    	}
    	
    	return null;
    }
    
    /**
     * 此方法是根据员工的编号去
     * 获取当前时间-入职时间=总天数*/
    private int getResultCount(Integer number){
    	
    	return userService.selectByRegistertime_NowTime(number);
    }
}
