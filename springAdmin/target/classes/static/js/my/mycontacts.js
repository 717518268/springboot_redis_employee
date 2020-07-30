 

 var app=angular.module('app',[]);
 	app.controller('mainController',['$scope','$http',function($scope,$http){
 		
 
 		//根据名字查询员工对应的信息
 		$scope.getUserForContent =function(){
	    		$http({
	     			method:'GET',
	        		 url:'/selectContentUser_aContacts',
	        		headers:{"Content-Type":'application/x-www-form-urlencoded'},
	     			}).success(function(data){
	     				if(data!=null){
	     					$scope.users=data;
	     				}else{
	     					$scope.users="";
	     				}	 
	     			});
	    		}
 		$scope.getUserForContent();//页面加载时
 		//修改员工编号
 		$scope.updateDepartmentNumber=function(number){
	 		if (confirm("确定要修改编号吗？"+number)){
	 			var updatenumber=prompt("原值是："+number+"请修改");
	 			$http({
	     			method:'POST',
	        		 url:'/UpdateNumberReq',
	        		headers:{
	        			"Content-Type":'application/x-www-form-urlencoded'
	        		},
	        		params:{updatenumber:updatenumber},	
	     			}).success(function(data){
	     				if(data==1){
	     					 alert("修改成功！");
	     					$scope.getUserForContent();
	     				}else if(data==-3){
	     					 alert("修改的值不能为空！");
	     				}else{
	     					alert("发生了未知错误");
	     				}		 
	     			});
	 		}
	 	}
 		//修改员工名字
 		$scope.updateName=function(username){
	 		if (confirm("确定要修改名字吗？"+username)){
	 			var updatename=prompt("原值是："+username+"请修改");
	 			$http({
	     			method:'POST',
	        		 url:'/updateName',
	        		headers:{"Content-Type":'application/x-www-form-urlencoded'},
	        		params:{updatename:updatename},	
	     			}).success(function(data){
	     				if(data==1){
	     					 alert("修改成功！");
	     					$scope.getUserForContent();
	     				}else if(data==-1){
	     					 alert("修改的值不能为空！");
	     				}else if(data==-2){
	     					alert("发生了未知错误");
	     				}		 
	     			});
	 		}
	 	}
	 	//修改入职时间
 		$scope.updateRegitserTime=function(registertime){
	 		if (confirm("确定要修改入职时间吗？"+registertime)){
	 			var newtimes=prompt("原值是："+registertime+"请安装日期xxxx-xx-xx xx:xx修改格式进行修改");
	 			$http({
	     			method:'POST',
	        		url:'/updateRegitserTime',
	        		headers:{"Content-Type":'application/x-www-form-urlencoded'},
	        		params:{newtimes:newtimes},
	     			}).success(function(data){
	     				if(data==1){
	     					 alert("修改成功！");
	     					$scope.getUserForContent();
	     				}else if(data==-1){
	     					 alert("修改的值不能为空！");
	     				}else if(data==-2){
	     					alert("发生了未知错误");
	     				}
	     				  
	     			});
	 		}
	 	}
 		//修改部门
 		$scope.updatedepartment=function(dname){
	 		if (confirm("确定要修改部门吗？"+dname)){
	 			var updname=prompt("原来的值是："+dname+"请按照已经存在的部门进行修改");
	 			$http({
	     			method:'POST',
	        		 url:'/updatedepartment',
	        		headers:{
	        			"Content-Type":'application/x-www-form-urlencoded'
	        		},
	        		params:{updname:updname},	
	        	//angular用此传值
	     			}).success(function(data){
	     				//alert("data=="+data);
	     				if(data==1){
	     					 alert("修改成功！");
	     					$scope.getUserForContent();
	     				}else if(data==-1){
	     					 alert("修改的值不能为空！");
	     				}else if(data==-2){
	     					alert("发生空指针异常");
	     				}else if(data==-3){
	     					alert("部门不存在");
	     				}
	     				 
	     				 
	     			});
	 		}
	 	}
 		
 		//查询休假列表
 		$scope.getLeaveRecordsList =function(){
	    		$http({
	     			method:'GET',
	        		 url:'/getLeave/Recordslist',
	        		headers:{
	        			"Content-Type":'application/x-www-form-urlencoded'
	        		},
	        	//	params:data1,	
	        	//angular用此传值
	     			}).success(function(data){			 
	     				$scope.LeaveRecordslis=data;
	     				$scope.getYESCount();
					});
	    		
	    		}
 		$scope.getLeaveRecordsList();
 		//根据年份查询休假列表
 		$scope.getLeaveRecordsListAndyears=function(){
 			var year=$("#yearType").val();
 			var date1={year:year};
 			 
    		$http({
     			method:'GET',
        		url:'/getLeave/Recordslist_year',
        		headers:{"Content-Type":'application/x-www-form-urlencoded'},
        		params:date1,
     			}).success(function(data){			 
     				$scope.LeaveRecordslis=data;
				});
    		
    		}
 		
 		//删除对应的休假信息
 		$scope.DeleteLeaveRecords=function(lid){
 			 
 			var date1={lid:lid};
 			if (confirm("确定要删除吗？")){
    		$http({
     			method:'GET',
        		url:'/DeleteLeaveRecords_lids',
        		headers:{"Content-Type":'application/x-www-form-urlencoded'},
        		params:date1,
     			}).success(function(data){			 
     				 if(data==1){
     					 alert("成功");
     					 //删除成后加载一次
     					$scope.getLeaveRecordsList();
     				 }else{
     					 alert("失败");
     				 }
				});
 			}
    		}
 		
 		//添加休假的对应的信息
 		$scope.sendLeava_holiday=function(){
 			var starttime=$scope.starttime;
 			var endtime=$scope.endtime;
 			var stauts=$("#stauts").val();
 			var reason=$scope.reason;
 			var data1={starttime:starttime,endtime:endtime,stauts:stauts,reason:reason};
	    		$http({
	     			method:'POST',
	        		 url:'/sendLeava_holiday',
	        		headers:{"Content-Type":'application/x-www-form-urlencoded'},
	        		params:data1,	
	     			}).success(function(data){
	     				if(data==1){
	     				 alert("ok");
	     				$scope.getUserForContent();
	     				$scope.getLeaveRecordsList();
	     				}else if(data==-3||data==-2){
	     					alert("输入出错");
	     					
	     				}else{
	     					alert("时间超出");
	     				}
	     				 
	     				 
	     			});
	    		
	    		}
 		//获取当前可休假的时间
 		$scope.getYESCount=function(){
    		$http({
     			method:'GET',
        		 url:'/getYESCount',
        		headers:{
        			"Content-Type":'application/x-www-form-urlencoded'
        		},
     			}).success(function(data){			 
     				$scope.getAllCount=data;
				});
    		
    		}
 		 
 		
 	}]);
     