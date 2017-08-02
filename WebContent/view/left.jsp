<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
	<head> 

		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<style>
			body{
				font-family: "微软雅黑";
			}
			.module-nav {
				width: 183px; 
				float:left;
				z-index:1; 
			}  

			.badge {
				background-color: #F8645C;
				margin-right:10px;
			}
			.navbar{
				margin-bottom: 0px; 
				z-index:1;
			}	
			.hide-logo{
				z-index:0;
				height: 51px;
				width: 183px; 
				background-color:red;
				top:0px;;
				position: fixed; 
			}  
			.glyphicon{
				margin-right:5px;
			}
			.list-group-item{
			 	border:0; 
			 	padding:0;
				border-left: 6px solid #fff; 
			}			
			.list-group-item a { 
			        text-decoration:none;
			        overflow: hidden; 
    				font-size: 14px; 
    				display: block;/*给a标签设置成块级元素*/
					width: 100%; 
					padding: 13px 0;
					padding-left:35px;
					color: black; 
			} 
			.select-tab{
				 border-left: 6px solid #2695ff; 
			}
			.select-tab>a,.select-tab .glyphicon{
			 	  color: #2695ff;
			 }
			 .select-tab .badge{
			 	  background-color:#2695ff;
			 }
			 .list-group-item:hover{
			 	cursor: pointer;
			 }
			 .list-group-item:hover >a, .list-group-item:hover .glyphicon{
			 	color: #2695ff;
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
			<ul class="list-group" id="left_nav">  
				<li class="list-group-item" id="notices">
					<a href="notices">
						<span class="glyphicon glyphicon-comment"></span> 学院通知
					</a>
				</li>
				<sec:authorize access="hasRole('TEACHER')">
				<li class="list-group-item" id="course">
					<a href="course">
						<span class="glyphicon glyphicon-list-alt"></span> 我的课程
					</a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasRole('TEACHER')">
				<li class="list-group-item" id="submit">
					<a href="submit">  
						<span class="glyphicon glyphicon-tasks"></span> 待提交
						<span class="badge pull-right">new</span>
					</a>
				</li>
				</sec:authorize>
				<sec:authorize  access="hasAnyRole('DIRECTOR','DEAN','ADMIN')">
				<li class="list-group-item" id="review">
					<a href="review">
						<span class="glyphicon glyphicon-folder-close"></span> 待审核
					</a>
				</li> 
				</sec:authorize>
				<sec:authorize access="hasAnyRole('DIRECTOR','DEAN','ADMIN')">
				<li class="list-group-item" id="status">
					<a href="status">
						<span class="glyphicon glyphicon-eye-open"></span> 提交情况
					</a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasRole('TEACHER')">
				<li class="list-group-item" id="declaration">
					<a href="declaration">
						<span class="glyphicon glyphicon-book"></span> 教材申报
					</a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('SECRETARY','ADMIN')">
				<li class="list-group-item" id="setting">
					<a href="setting">
						<span class="glyphicon glyphicon-cog"></span> 设置
					</a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('DIRECTOR','DEAN','SECRETARY','ADMIN')">
				<li class="list-group-item" id="Announcement">
					<a href="Announcement">
						<span class="glyphicon glyphicon-send"></span> 发布通知
					</a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('SECRETARY','ADMIN')">
				<li class="list-group-item" id="users">
					<a href="users">
						<span class="glyphicon glyphicon-wrench"></span> 用户信息
					</a>
				</li>
				</sec:authorize>
				<li class="list-group-item" id="statistics" style="border-bottom: 2px solid #d8dfea; ">
					<a href="statistics">
						<span class="glyphicon glyphicon-signal"></span> 统计信息
					</a>
				</li>
				
				<li class="list-group-item" id="docs">
					<a href="docs">
						<span class="glyphicon glyphicon-cloud"></span> 我的文档
					</a>
				</li>
				<li class="list-group-item" id="info">
					<a href="info">
						<span class="glyphicon glyphicon-user"></span> 个人信息
					</a>
				</li>
				<li class="list-group-item" id="update">
					<a href="update">
						<span class="glyphicon glyphicon-refresh"></span> 版本更新
					</a>
				</li>
				<li class="list-group-item" id="help">
					<a href="help">
						<span class="	glyphicon glyphicon-question-sign"></span> 帮助中心
					</a>
				</li>
				<li class="list-group-item" id="feedback">
					<a href="feedback">
						<span class="glyphicon glyphicon-send"></span> 反馈
					</a>
				</li>
				<li class="list-group-item" id="logout">
					<a href="logout">
						<span class="glyphicon glyphicon-off"></span> 退出
					</a>
				</li>
			</ul>
		</div> 
	</body> 
	<script type="text/javascript">
		document.getElementById(window.location.pathname.split('/').pop()).className ='list-group-item select-tab';
	</script>
</html>