<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>提交文档</title>
<link rel="stylesheet" href="<%=basePath %>css/ctdms.css" />

	<jsp:include page="left.jsp" flush="true" />
	<link rel="stylesheet" href="<%=basePath %>css/ctdms_submit.css" />
	<style>
	.col-md-8{
		width:100%;
	}
	.doc-type{
		float:left;
		width:24px; 
		height: 30px; 
	}
	.uploadForm{
		width:100px;
		float:right;
	}
	</style>
</head>
<body> 
<div id="top-progress-bar"></div> 
	<div class="" >
		<div class="container module-frame" id="iframe">
			<div class="row">
				<div class="col-md-8">
					<div class="tab" role="tabpanel">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" id="teach"><a
								href="submit?tab=teach" role="tab" data-toggle="tab">教学进度表</a></li>
							<li role="presentation" id="syllabus"><a
								href="submit?tab=syllabus" role="tab" data-toggle="tab">课程教学大纲</a>
							</li>
							<li role="presentation" id="practice"><a
								href="submit?tab=practice" role="tab" data-toggle="tab">实践教学大纲</a>
							</li>
							<li role="presentation" id="other"><a
								href="submit?tab=other" role="tab" data-toggle="tab">其他</a></li>
						</ul>
						<div class="tab-content tabs">
							<div role="tabpanel" class="tab-pane fade in active" id="panel">
							 
									<section class="s-section">
										<div class="sign-bg sign-bg-fail">未提交</div>
										<h4>软件测试</h4> 
										<p>代码:0ASDAS</p>
										<p>班次:B04</p>
										<p>开始时间：2017-04-28 23:00:00</p>
										<p>结束时间：2017-04-28 23:00:00</p>
										 
											<div class="s-content-bottom">
											<span class="repo-language-color pinned-repo-meta" style="background-color:#b07219;">
											</span>
												<span class="doc-type">
												 	<div class="fileicon doc-small"></div> 
												</span>  
													<form class="uploadForm" action=""  enctype="multipart/form-data">	
														<a class="btn btn-primary btn-large btn-block file uploadBtn" href="#">
													 		上传
															<input type="file" name="file" >
														</a>
													</form>  
											</div>
										 
									
									</section>  
							 
								<div class="clear"></div>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="<%=basePath%>js/ToProgress.min.js" ></script>
<script>

					var options = {
					  id:'top-progress-bar',
					  color: '#F44336',
					  duration: 0.2,
					  height: '3px'
					};
					var topbar = new ToProgress(options);    
					topbar.setProgress(50);
					
			  	function getUrlFrame(name){
				     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
				     var r = window.location.search.substr(1).match(reg);
				     if(r!=null) return  unescape(r[2]); 
				     return  'teach';
				} 
				window.onload=function(){
					var tab = getUrlFrame('tab');
					document.getElementById(tab).className="active";
				}
				
	</script>
</html>
