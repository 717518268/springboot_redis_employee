 	function InsertDepartmentValue(){
			var dname=$("#departmentValue").val();
			 
			
			$.ajax({
				url:'/insert_Controller/Department',
				type:'POST',
				dataType : "json",
				data:{dname:dname},
				success:function(data){
					 if(data==-1){
						 alert("编号出现重复或者是空值 ");
					 }else if(data==-2){
						 alert("汉字匹配不正确");
					 }else if(data==-3){
						 
					 }else{
						 alert("添加成功！！！");
						 window.location.href="/user";
					 }
				}
			});
		}