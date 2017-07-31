<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>学院教学文档管理系统</title>
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/ctdms.css" />
</head>
<body>
	<div class="wallpaper">
		<div class="signup-form fadeInUp animated">
			<div class="signup-form__logo-box">
				<div class="signup-form__logo"></div>
				<div class="signup-form__catchphrase">让文档提交变得更有效率</div>
			</div>
			<div id="container-login">
				<div data-reactroot="" id="LoginComponent">
					<span>
						<form action="<c:url value='login2' />" method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="input-field-group">
								<div class="input-field">
									<input type="text" name="userName" placeholder="教师姓名/ctdms ID"
										autocomplete="false" autocapitalize="off">
								</div>
								<div class="input-field">
									<input type="password" name="passWord" placeholder="密码"
										autocomplete="false" autocapitalize="off">
								</div>
							</div>

							<ul class="error-msg-list fadeInUp animated">
								<c:if test="${!empty error }">
									<li class="error-msg-list__item">${error }</li>
								</c:if>
								<c:if test="${!empty logout }">
									<li class="error-msg-list__item">${logout }</li>
								</c:if>
							</ul>

							<button type="submit" class="signup-form__submit">登录</button>
							<div class="signup-form-nav">
								<div class="left"></div>
								<div class="right">
									<a href="" target="_blank">忘记了</a>
								</div>
							</div>
						</form>
					</span>
				</div>
			</div>
			<div class="signup-form__sns-btn-area">
				<div>江西财经大学-软通学院</div>
				<div class="sns-button-list"> 
				
				</div>
			</div>
		</div>
	</div>
</body>
<!-- 这骚操作 吃电脑配置
	<script type="text/javascript" src="js/jquery.min.js" ></script>
	<script type="text/javascript" src="js/ios-parallax.js" ></script>
	<script> 
	    $(document).ready(function() {
	      $('.wallpaper').iosParallax({
	        movementFactor: 50
	      });
	    }); 
	</script>
	 -->
</html>