<!DOCTYPE html>
<html> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>通知</title>
		<link rel="stylesheet" href="css/notices.css" />
	</head>

	<body>
		<div class="newsticker">
			<ul class="newsticker-list">
				<li class="newsticker-item">
					通知
				</li>
				<li class="newsticker-item">
					再通知:That creature his bring waters...
				</li>
				<li class="newsticker-item">
					再次通知:And also. Firmament and Give....
				</li>
			</ul>
		</div>

		<div class="container" id="news">
			<dl class="msgtype"> 学院消息</dl>
			<dl>
				<dt>消息测试</dt>
				<dd style="display: block;">
					<p>内容......</p>
					<p>内容......</p>
					<p>
						<span class="time">2017年1月15日</span>
						<span class="user">管理员</span>
					</p>
				</dd>
			</dl>
			<dl>
				<dt>标题</dt>
				<dd>
					<p>内容......</p>
					<p>内容......</p>
				</dd>
			</dl>
			<dl>
				<dt>标题</dt>
				<dd>
					<p>内容......</p>
					<p>内容......</p>
				</dd>
			</dl>
			<div class="more">
				<a href="#">查看更多</a>
			</div>
		</div>

		<div class="container" id="version">
			<dl class="msgtype"> 系统最新动态</dl>
			<dl>
				<dt>新增教师课表</dt>
				<dd style="display: block;">
					<p>-参照学校教务系统课表显示风格</p>
					<br>
					<p>
						<span class="time">2017年7月15日</span>
						<span class="user">开发人员</span>
					</p>
				</dd>
			</dl>
			<dl>
				<dt>重新制作了登录界面</dt>
				<dd>
					<p>1.登录界面采用极简的风格,删除了大量的冗余功能.</p>
					<br>
					<p>
						<span class="time">2017年7月15日</span>
						<span class="user">开发人员</span>
					</p>
				</dd>
			</dl>
			<dl>
				<dt>标题</dt>
				<dd>
					<p>内容......</p>
					<p>内容......</p>
				</dd>
			</dl>
			<dl>
				<dt>标题</dt>
				<dd>
					<p>内容......</p>
					<p>内容......</p>
				</dd>
			</dl>
			<div class="more">
				<a href="#">查看更多</a>
			</div>
		</div>
 

		<div class="container" id="mymessage">
			<dl class="msgtype"> 我的消息</dl>
			<dl> <dt>暂无更多消息</dt>
			</dl>
		</div>
	</body>

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.newsticker.js"></script>
	<script>
		(function() {

			$('dd').filter(':nth-child(n+4)').addClass('hide');
			$('dl').on('click', 'dt', function() {
				$(this).next().slideToggle(200);
			});
		})();
	</script>

</html>