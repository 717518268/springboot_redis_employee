 
		 	//根据id搜索学生
		  
		 	
		 	 var app=angular.module('app',[]);
		    	app.controller('mainController',['$scope','$http',function($scope,$http){
		    		
		    		//根据部门编号查询部门对应的员工
		    		$scope.getStudentByClass =function(){
		    			
		    		 
		    		var did=$("#classType").val();
		    		var data1={did:did};
		    		$http({
		     			method:'GET',
		        		 url:'/getListUserForRequest',
		        		headers:{
		        			"Content-Type":'application/x-www-form-urlencoded'
		        		},
		        		params:data1,	//angular用此传值
		     			}).success(function(data){
		     				$scope.commentArr=data;
		     				 
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
			     				$scope.commentArrayName=data;
			     				 
			     			});
			    		
			    		}
 
		    		
		    	}]);
		        