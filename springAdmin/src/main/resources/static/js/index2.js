 layui.use('form', function(){
			  var form = layui.form
			  var $ = layui.$;
		    $ (function(){
				 
				 
				
				$.ajax({
					url:"/get_Sort_years_List",
					type:"GET",
					dataType : "json",
					success:function(data){
						
						for(var i=0;i<data.length;i++){
							$("#yearType").append("<option value='"+data[i]+"'>"+ data[i]+ "</option>");
							 
						}
						form.render();//注意渲染页面表单，否则不显示数据
					}
				});
				
				
				
				
				//get_Sort_years_List
		    });
 });
		 	//根据id搜索学生
		  
		 	 
		        