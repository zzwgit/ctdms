<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
	<head> 
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<style>
			body{
				font-family: "微软雅黑";
			}
			.module-nav {
				width: 153px; 
				float:left;
				z-index:1; 
			} 
			#left_nav a {
				color: black;
			}
			#left_nav .nav>li>a { 
			    padding: 10px 5px 10px 25px;
			} 
			.badge {
				background-color: #F8645C;
			}
			.navbar{
				margin-bottom: 0px; 
				z-index:1;
			}	
			.hide-logo{
				z-index:0;
				height: 51px;
				width: 153px; 
				background-color:red;
				top:0px;;
				position: fixed; 
			}
		</style>
	</head>

	<body>
		  
		<nav class="navbar navbar-default" role="navigation" >
				<div class="navbar-header">
					<a class="navbar-brand" href="#">ctdms-beta</a>
					<a class="navbar-brand" href="#">v2.0</a>
				</div>
		</nav>  
		<div class="hide-logo">这是一个隐藏的logo</div>
		<div class="module-nav"  >
			<ul class="nav nav-pills nav-stacked" id="left_nav"> 
				<li>
					<a href="notices">
						<span class="glyphicon glyphicon-comment"></span> 学院通知
					</a>
				</li>
				<li>
					<a href="course">
						<span class="glyphicon glyphicon-list-alt"></span> 我的课程
					</a>
				</li>
				<li>
					<a href="submit">

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
					<a href="Announcement">
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
					<a href="report">
						<span class="glyphicon glyphicon-cloud"></span> 帮助中心
					</a>
				</li>
				<li>
					<a href="report">
						<span class="glyphicon glyphicon-cloud"></span> 反馈
					</a>
				</li>
				<li>
					<a href="javascript:iframeChang('allDoc-frame');">
						<span class="glyphicon glyphicon-cloud"></span> 退出
					</a>
				</li>
			</ul>
		</div>
		 
		  
	</body> 
</html>