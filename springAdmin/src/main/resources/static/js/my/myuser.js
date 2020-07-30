	//根据id搜索学生
		  
		 	
		 	 var app=angular.module('app',[]);
		    	app.controller('mainController',['$scope','$http',function($scope,$http){
		    		
		    		//根据部门编号查询部门对应的员工
		    		$scope.getStudentByClass =function(){
		    			
		    		 
		    		var did=$("#classType").val();
		    		var year=$("#yearType").val();
		    		var data1={did:did,year:year};
		    		$http({
		     			method:'GET',
		        		 url:'/getListUserForRequest',
		        		headers:{
		        			"Content-Type":'application/x-www-form-urlencoded'
		        		},
		        		params:data1,	//angular用此传值
		     			}).success(function(data){
		     				if(data==null){
		     					alert("请正确选择");
		     				}else{
		     					$scope.commentArr=data;
		     				}
		     				 
		     				 
		     			});
		    		
		    		}
		    		
		    		
		    		//根据名字查询员工对应的信息
		    		$scope.getUserNameForContent =function(){
		    			
			    		 
			    		var username=$scope.username;
			    		var data1={username:username};
			    		$http({
			     			method:'GET',
			        		 url:'/getUsernameForContent',
			        		headers:{
			        			"Content-Type":'application/x-www-form-urlencoded'
			        		},
			        		params:data1,	//angular用此传值
			     			}).success(function(data){
			     				if(data!=null){
			     					$scope.commentnams=data;
			     				}else{
			     					$scope.commentnams="";
			     				}
			     				 
			     				 
			     			});
			    		
			    		}
		    		
		    		/** 休假申请设置 ***/
		    		$scope.updateUserStauts=function(id,username,registertime){
		    			if(confirm(username+"确定要设置离职吗？")){
		    				var outtime=prompt("入职时间是："+registertime+"请安装日期xxxx-xx-xx xx:xx修改格式进行离职设置");
		    				
							$http({
				     			method:'POST',
				        		 url:'/SetUpdateUserStautsAnd_delete',
				        		headers:{
				        			"Content-Type":'application/x-www-form-urlencoded'
				        		},
				        		params:{id:id,username:username,outtime:outtime},	//angular用此传值
				     			}).success(function(data){
				     				 if(data>0){
				     					 alert("离职成功");
				     					 $scope.getStudentByClass();
				     					$scope.getUserNameForContent();
				     					$scope.username="";
				     				 }else{
				     					 alert("服务器异常");
				     				 }
				     				 
				     			});
						}else{
							
						}
		    		}
		    		
		    		
		    		//deleteUserById
					$scope.deleteoneEmptUser=function(id){
		    			
						if(confirm("确定要删除吗？")){
						$http({
			     			method:'GET',
			        		 url:'/deleteUserById',
			        		headers:{
			        			"Content-Type":'application/x-www-form-urlencoded'
			        		},
			        		params:{id:id},	//angular用此传值
			     			}).success(function(data){
			     				 
			     				 if(data>0){
			     					 alert("删除成功");
			     					 $scope.getStudentByClass();
			     				 }else{
			     					 alert("删除失败");
			     					 
			     				 }
			     				 
			     			});
					}
		    		}
		    		
		    	}]);
		        