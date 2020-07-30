 		$("#loginClict").click(function () {
				var username=$("#username").val();
				var password=$("#password").val();
	             $.ajax({
	            	type:'POST',
	            	data:{username:username,password:password},
	            	url:'/loginMainPethod',
	            	success:function(data){
	            		
	            		if(data==1){
	            			alert("登录成功");
	            			window.location.href="/";
	            		}else{
	            			alert("不正确");
	            		}
	            		 
	            	}
	             });
	         });
