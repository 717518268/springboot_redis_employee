 $(function(){
			layui.use('form', function(){
				  var form = layui.form
				  var $ = layui.$;
			    $ (function(){
					 
					$.ajax({
						url:"/getDepartmentlist",
						type:"GET",
						dataType : "json",
						success:function(data){
							
							for(var i=0;i<data.length;i++){
								$("#classType").append("<option value='"+data[i].nid+"'>"+ data[i].dname+ "</option>");
								 
							}
							form.render();//注意渲染页面表单，否则不显示数据
						}
					});
			    });
		});
			 	//根据id搜索学生
			 
		})
	 
		function sendInsertUser(){
			var newnumber=$("#newnumber").val();
			var username=$("#username").val();
			var sex=$("#sex").val();
			var registertime=$("#registertime").val();
			var department=$("#classType").val();
			
			$.ajax({
				url:'/insertUser_for_Context',
				type:'POST',
				dataType : "json",
				data:{newnumber:newnumber,username:username,sex:sex,registertime:registertime,department:department},
				success:function(data){
					 if(data==-1){
						 alert("编号出现重复或者是空值 ");
					 }else if(data==-2){
						 alert("汉字匹配不正确");
					 }else if(data==-3){
						 alert("错误");
					 }else{
						 alert("添加成功！！！");
						 $("#newnumber").val("");
						 $("#username").val("");
					 }
				}
			});
		}