<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>提交文档</title>
<link rel="stylesheet" href="<%=basePath%>css/ctdms.css" />

<jsp:include page="left.jsp" flush="true" />
<link rel="stylesheet" href="<%=basePath%>css/ctdms_submit.css" />
<style>
.col-md-8 {
	width: 100%;
}

.doc-type {
	float: left;
	width: 24px;
	height: 30px;
}

.uploadForm {
	width: 100px;
	float: right;
}

#top-progress-bar {
	z-index: 2;
}

.green {
	background-color: #7a81f4;
}

.line {
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	height: 3px;
	width: 50%;
	transition: width 0.2s, opacity 0.6s;
	opacity: 1;
}

.btn-primary {
	background-color: white;
	color: black;
	font-weight: bold;
}
</style>
</head>
<body>
	<div id="top-progress-bar"></div>
	<div class="">

		<div class="container module-frame" id="iframe">
			<div class="frame-title">
				<h5>待提交的文档</h5>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="tab" >
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" >
							<li  id="teach"><a href="submit"
								 >教学进度表</a></li>
							<li  id="syllabus"><a
								href="submit?tab=syllabus" >课程教学大纲</a>
							</li>
							<li   id="practice"><a
								href="submit?tab=practice" >实践教学大纲</a>
							</li>
							<li   id="other"><a
								href="submit?tab=other" >其他</a></li>
						</ul>
						<div class="tab-content tabs  fadeInUp animated">
							<div   class="tab-pane fade in active" id="panel">
								<c:forEach var="s" items="${subdoc }">
									<section class="s-section">
										<div class="line green"></div>

										<div class="sign-bg sign-bg-${s.bgcolor }">${s.stateMsg }</div>
										<h4>${s.name }</h4>
										<c:forEach var="i" items="${s.docInfos }">
											<p>${i }</p>
										</c:forEach>
										<div class="s-content-bottom">
											<span class="repo-language-color pinned-repo-meta"
												style="background-color:#b07219;"> </span> 
												<span
												class="doc-type">
												<div class="fileicon ${s.type }-small" ></div>
											</span>
											<form class="uploadForm"  action="<%=basePath %>upload" method="post" enctype="multipart/form-data">
											
												<input type="hidden" name="id"  value="${s.id }" /> 
												<input type="hidden" name="userId"  value="${userId }" /> 
												<input type="hidden" name="tab" value="${tab }" /> 
												<div class="btn btn-primary btn-large btn-block file uploadBtn"> 
												上传  
												<input type="file" name="file" /> 
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  
												</div>  
											</form>
										</div> 
									</section>
									
								</c:forEach>
								<div class="clear"></div> 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-form.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ToProgress.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
<script>
	//进度条
	var options = {
		id : 'top-progress-bar',
		color : '#F44336',
		duration : 0.2,
		height : '3px'
	};
	var topbar = new ToProgress(options);
	//获得上传进度
	var oTimer;
	function getProgress() {
		$.ajax({
			url : "progress",
			dataType : "json",
			type: 'get',
			success : function(data) {
				if (data == 'error' || data.percent == "100") {
					window.clearInterval(oTimer); //清除定时器	 
					
				}else{
					topbar.setProgress(data.percent);
				}
			},
			error : function(err) {
				window.clearInterval(oTimer); //清除定时器	 
			}
		});
	}
	function upload_ps() {
		if(!oTimer)
			oTimer = setInterval("getProgress()", 500);
	}

	function bindSubmit() {
		$('.uploadForm').bind("submit", function() {
			var options = {
				dataType : "json", 
				type : "post",
				success : function(data) {
				}
			};
			$(this).ajaxSubmit(options);
			upload_ps();
			return false;
		});
	}
	function change() {
		$(".uploadBtn").on("change", "input[type='file']", function() {
			var filePath = $(this).val();
			if (filePath.indexOf("doc") != -1) {
				$(this).parent().submit();
			}
			return false;
		});
	}
 
	window.onload = function() {
		var tab = getUrlFrame('tab');
		document.getElementById(tab).className = "active";

		bindSubmit();
		change();
	};
</script>
</html>