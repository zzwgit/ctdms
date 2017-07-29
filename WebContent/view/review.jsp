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

.text-explode {
	color: #CCC !important;
	font-weight: normal !important;
	margin: 0px 4px !important;
}

a {
	color: #06C;
	cursor: pointer;
}

td,th {
	text-align: center;
}
</style>
</head>
<body>
	<div id="top-progress-bar"></div>
	<div class="">

		<div class="container module-frame" id="iframe">
			<div class="frame-title">
				<h5>待审核的文档</h5>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="tab">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs">
							<li id="teach"><a href="review">教学进度表</a></li>
							<li id="syllabus"><a href="review?tab=syllabus">课程教学大纲</a>
							</li>
							<li id="practice"><a href="review?tab=practice">实践教学大纲</a>
							</li>
							<li id="other"><a href="review?tab=other">其他</a></li>
						</ul>
						<div class="tab-content tabs  fadeInUp animated">
							<div class="tab-pane fade in active" id="panel">

								<table class="table table-hover">
									<thead>
										<tr>
											<th><select class="select-primary">
													<option value="all" selected>全部</option>
													<option value="unsub">未提交</option>
													<option value="wait">待审核</option>
													<option value="pass">已通过</option>
													<option value="failure">未通过</option>
											</select></th>
											<th>课程</th>
											<c:if test="${(tab)!= null && tab=='teach' }">
												<th>班次</th>
											</c:if>
											<th>上传者</th>
											<th>最后一次修改时间</th>
											<th>审核</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="s" items="${docs }">
											<tr>
												<td>待审核</td>
												<td><a class="cin" href="javascript:openPDF('')">${s.name }</a>
												</td>
												<c:forEach var="i" items="${s.docInfos }">
													<td>${i }</td>
												</c:forEach>
												<td><a class="cback"
													href="javascript:download(${s.docId })">下载</a> <span
													class="text-explode">|</span> <a class="cpass"
													href="javascript:review(${s.id },${tab },1)">通过</a> <span
													class="text-explode">|</span> <a class="cback"
													href="javascript:review(${s.id },${tab },0)">打回</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var tab = getUrlFrame('tab');
		document.getElementById(tab).className = "active";
	};
	function review(cid, tab, pass) {
		$.post({
			url : "review",
			dataType : "json",
			data : {
				id : cid,
				tab : tab,
				isPass : pass
			},
			success : function(data) {
			},
			error : function(err) {
			}
		});
	}
	function download(id) {
		$.post({
			url : "download",
			dataType : "json",
			data : {
				docId : id
			},
			success : function(data) {
			},
			error : function(err) {
			}
		});
	}
</script>
</html>