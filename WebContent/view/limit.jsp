<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<title></title>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap-datepicker.min.css" />
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css"> 
<style>

.dateMessage_carry{
	width:400px;
	padding: 30px;
	height: 400px;
	margin:0px auto  ; 
}
.form-group .btn-primary{ 
	width: 30%;
}</style>
</head>
<body>
	<div class="dateMessage_carry">
		<form class="form-horizontal" id="infoForm"  role="form">
			<div class="form-group">
				<label for="name">标题: <span class="label label-warning">选填</span></label>
				<input type="text" class="form-control" id="title" name="title"
					placeholder="请输入标题">
			</div>
			<div class="form-group">
				<label for="name">具体内容: <span class="label label-warning">选填</span></label>
				<textarea rows="6" class="form-control" id="detail" name="detail"
					placeholder="请输入具体内容"></textarea> 
			</div>

			<div class="form-group">
				<label for="name">提交文档日期范围: <span class="label label-danger">必填</span></label>
				<div class="input-daterange input-group" id="datepicker">
					<input type="text" class="form-control" name="start" id="qBeginTime" placeholder="开始日期"/> 
					<span class="input-group-addon">至</span>
					<input type="text" class="form-control" name="end" id="qEndTime"  placeholder="结束日期"/>
				</div>
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-primary"
					onclick="javascript:Submit();" class="btn btn-default">确定</button>
			</div>
		</form>


	</div>
</body>

<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script> 

<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=basePath%>js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/bootstrap-datepicker.zh-CN.min.js"></script>

<script> 
		function dateReplace(e){
			e=e.replace("年","-");
			e=e.replace("月","-");
			e=e.replace("日","");
			return e;
		}
		function Submit(){
			var beginVal = $("#qBeginTime").val();
			var endVal = $("#qEndTime").val();
			if(beginVal.length<10||endVal.length<10){
				alert("请选择日期");
				return;
			} 
			var start = dateReplace(beginVal);
			var end = dateReplace(endVal);
			console.log(start+"  "+end); 
			
			var title = $("#title").val();
			var detail = $("#detail").val();
			if(title.length<=0)
				title='...';
			if(detail.length<=0)
				detail='...';
		    var url="setLimitDate";
		    url+="?title="+title+"&detail="+detail;
		    url+="&start="+start+"&end="+end;
		    console.log(url); 
		    ajaxSubmit(url);
		} 
		function ajaxSubmit(url){ 
		      	var options = {  
	         		url:url,
	         		type : "post", 
	        	    success: function (data) {  
	        	    	parent.window.location.href="limitDate"; 
	                },
	                error: function (data){ 
	                  $(".btn-default").html('确定');
	                 	alert('服务器未响应');
	                }
	         };
	         $(".btn-default").html('修改中...');
			$.ajax(options);  
		} 
		
		$('#qBeginTime').datepicker({ 
			todayHighlight:true,
			language: 'zh-CN', 
			autoclose : true, 
		}).on('changeDate',function(e){
			var startTime = e.date;
			$('#qEndTime').datepicker('setStartDate',startTime);
		});
		//结束时间：
		$('#qEndTime').datepicker({
			todayHighlight:true, 
			language: 'zh-CN', 
			autoclose : true, 
		}).on('changeDate',function(e){
			var endTime = e.date;
			$('#qBeginTime').datepicker('setEndDate',endTime);
		}); 
		
		
	</script>
</html>
