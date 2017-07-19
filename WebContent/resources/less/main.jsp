<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>教学文档管理系统</title> 
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<style>
			.module-nav {
				width: 150px;
				float:left; 
			} 
			.badge {
				background-color: #F8645C;
			}
			#iframe{ 
				width:100%;
				height: 100%;
			}
			a {
				color: black;
			}
			.nav>li>a { 
			    padding: 10px 5px 10px 25px;
			}
			.module-frame{
				border-top: 1px solid #d8dfea;
			    border-left: 1px solid #d8dfea;
			    border-right: 1px solid #d8dfea;
			    margin-left: -1px;    
			    top: 52px;
			    left: 152px;
			    bottom: 0px;
			    right: 5px;
			    height:100%;
			    border-top-left-radius: 5px;
			    border-top-right-radius: 5px;
			    position: absolute;
			}
		</style>
	</head>

	<body>
		
		<nav class="navbar navbar-default" role="navigation" style=" margin-bottom:0px;">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">ctdms-beta</a>
					<a class="navbar-brand" href="#">v2.0</a>
				</div>
			</div>
		</nav>

		<div class="module-nav"> 
			<ul class="nav nav-pills nav-stacked">
				<li>
					<a href="javascript:iframeChang('${userId }/notices');">
						<span class="glyphicon glyphicon-comment"></span> 学院通知
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('${userId }/mycourse');">
						<span class="glyphicon glyphicon-list-alt"></span> 我的课程
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang(' ');">

						<span class="badge pull-right">22</span>

						<span class="glyphicon glyphicon-tasks"></span> 待提交文档
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('');">
						<span class="glyphicon glyphicon-folder-close"></span> 待审核文档
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('textBookList');">
						<span class="glyphicon glyphicon-book"></span> 教材申报
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('classComplete');">
						<span class="glyphicon glyphicon-eye-open"></span> 提交情况
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('limitDate');">
						<span class="glyphicon glyphicon-cog"></span> 设置
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('${userId }/delivernotice');">
						<span class="glyphicon glyphicon-send"></span> 发布通知
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('userManage');">
						<span class="glyphicon glyphicon-wrench"></span> 用户信息
					</a>
				</li>

				<li>
					<a href="javascript:iframeChang('findDoc');">
						<span class="glyphicon glyphicon-signal"></span> 统计信息
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('allDoc-frame');">
						<span class="glyphicon glyphicon-cloud"></span> 我的文档
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('allDoc-frame');">
						<span class="glyphicon glyphicon-cloud"></span> 个人信息
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('allDoc-frame');">
						<span class="glyphicon glyphicon-cloud"></span> 版本更新
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('allDoc-frame');">
						<span class="glyphicon glyphicon-cloud"></span> 退出
					</a>
				</li>
			</ul>
		</div>
		
		<div class="module-frame">
			<iframe id="iframe" src="" frameborder="0" 
	 	 scrolling="yes"></iframe> 
		</div>
		
		  
	</body>
	  <script>
	  	var timer;
	  	window.onload=function(){ 
	  	} 
	  	
	  	function getUrlFrame(name){
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null) return  unescape(r[2]); 
		     return  '${userId }/notices';
		} 
	  	function iframeChang(e){
	  		
			if(document.getElementById("iframe").src==e)
				return false;
		//	$("#iframe").attr("src",e);
			location.href = "?frame="+e;
		}
	  	var frame = getUrlFrame('frame'); 
		document.getElementById("iframe").src= frame ;
   
	  </script>
</html>